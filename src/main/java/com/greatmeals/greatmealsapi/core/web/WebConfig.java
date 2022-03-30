package com.greatmeals.greatmealsapi.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // habilita cors no projeto inteiro
        .allowedMethods("*");
//        .allowedOrigins("*");
//        .allowedMethods("GET", "HEAD", "POST");
//        .maxAge(30);
    }
}
