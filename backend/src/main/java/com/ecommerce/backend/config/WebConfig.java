package com.ecommerce.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS to all endpoints under /api
                .allowedOrigins("*")   // Allow any frontend to connect (fine for development)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}