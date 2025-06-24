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
                .allowedOrigins("*")  // In production, restrict this to your app's domain
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}