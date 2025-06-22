// filepath: /Users/kabirsharma/Desktop/projects/proj2/backend/src/main/java/com/ecommerce/backend/config/SecurityConfig.java
package com.ecommerce.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection, common for stateless REST APIs
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow all requests to /api/** to be publicly accessible
                .requestMatchers("/api/**").permitAll()
                // All other requests should be authenticated
                .anyRequest().authenticated()
            )
            // If you want to keep the default login form for other routes
            .formLogin(withDefaults()); 
        return http.build();
    }
}