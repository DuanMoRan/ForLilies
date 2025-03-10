package com.example.tool;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private final SecretKey key = Keys.hmacShaKeyFor(
            "your-256-bit-secret-key-1234567890abcdef".getBytes(StandardCharsets.UTF_8));
    private final long EXPIRATION_TIME = 86400000;

    public String generateToken(String userName) {
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
