package com.example.goods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
public class PageConfig {
    @Bean
    PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor(DbType.SQL_SERVER);
    }
}
