// package com.ecommerce.backend.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// // @Configuration
// // public class WebConfig implements WebMvcConfigurer {
// //     @Override
// //     public void addCorsMappings(CorsRegistry registry) {
// //         registry.addMapping("/api/**") // Apply CORS to all endpoints under /api
// //                 .allowedOrigins("*")   // Allow any frontend to connect (fine for development)
// //                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
// //     }
// // }
// @Configuration
// @EnableWebMvc
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//             .allowedOrigins("*")  // In production, specify actual origins
//             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//             .allowedHeaders("*")
//             .allowCredentials(false)
//             .maxAge(3600);
//     }
// }
package com.ecommerce.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8081", "http://localhost:19006", "http://192.168.1.6:8081")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*", "Authorization")
            .exposedHeaders("Authorization")
            .allowCredentials(true)
            .maxAge(3600);
    }
}