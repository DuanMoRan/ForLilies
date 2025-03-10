package com.example.config;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.tool.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {
    private final JwtUtil jwtUtil;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private List<String> permitPaths = List.of(
            "/user/login",
            "/user/register",
            "/course/find/all",
            "/select/**");

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @SuppressWarnings("null")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String currentPath = exchange.getRequest().getPath().toString();

        boolean isPermit = permitPaths.stream()
                .anyMatch(pattent -> pathMatcher.match(pattent , currentPath));

        if(isPermit) return chain.filter(exchange);
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("Authorization"))
                .flatMap(token -> {
                    if (token.startsWith("Bearer ")) {
                        String jwt = token.substring(7);
                        if (jwtUtil.validateToken(jwt)) {
                            Authentication auth = new UsernamePasswordAuthenticationToken(
                                    jwtUtil.extractUsername(jwt),
                                    null,
                                    List.of(new SimpleGrantedAuthority("USER")));
                            return chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
                        }
                        return Mono.error(new AccessDeniedException("Invalid token"));
                    }
                    return Mono.error(new AccessDeniedException("Missing Bearer token"));
                })
                .onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                })
                .switchIfEmpty(Mono.defer(() -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }));

    }

}