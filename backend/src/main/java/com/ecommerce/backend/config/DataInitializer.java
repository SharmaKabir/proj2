// package com.ecommerce.backend.config;

// import com.ecommerce.backend.model.Product;
// import com.ecommerce.backend.repository.ProductRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import java.math.BigDecimal;
// import java.util.List;

// @Component
// @RequiredArgsConstructor
// public class DataInitializer implements CommandLineRunner {

//     private final ProductRepository productRepository;

//     @Override
//     public void run(String... args) {
//         // This check prevents seeding data every time you restart during a session
//         if (productRepository.count() == 0) {
//             Product p1 = Product.builder().name("Smartphone X").description("The latest model with a stunning display and AI-powered camera.").price(new BigDecimal("799.99")).imageUrl("https://i.imgur.com/l22aG2A.jpeg").build();
//             Product p2 = Product.builder().name("Wireless Headphones").description("Noise-cancelling over-ear headphones with 30-hour battery life.").price(new BigDecimal("199.99")).imageUrl("https://i.imgur.com/nS0rC6O.jpeg").build();
//             Product p3 = Product.builder().name("Smartwatch 2").description("Track your fitness, heart rate, and receive notifications on the go.").price(new BigDecimal("249.50")).imageUrl("https://i.imgur.com/s38i8p9.jpeg").build();
//             Product p4 = Product.builder().name("Laptop Pro").description("A powerful and lightweight laptop for professionals and creatives.").price(new BigDecimal("1299.00")).imageUrl("https://i.imgur.com/p8gG1zE.jpeg").build();

//             productRepository.saveAll(List.of(p1, p2, p3, p4));
//             System.out.println("--- Database has been seeded with 4 sample products. ---");
//         }
//     }
// }
package com.ecommerce.backend.config;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.User; // <-- Import User model
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository; // <-- Import UserRepository
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- Import PasswordEncoder
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository; // <-- Add UserRepository
    private final PasswordEncoder passwordEncoder; // <-- Add PasswordEncoder

    @Override
    public void run(String... args) {
        // Seed products
        if (productRepository.count() == 0) {
            Product p1 = Product.builder().name("Smartphone X").description("The latest model with a stunning display and AI-powered camera.").price(new BigDecimal("799.99")).imageUrl("https://i.imgur.com/l22aG2A.jpeg").build();
            Product p2 = Product.builder().name("Wireless Headphones").description("Noise-cancelling over-ear headphones with 30-hour battery life.").price(new BigDecimal("199.99")).imageUrl("https://i.imgur.com/nS0rC6O.jpeg").build();
            Product p3 = Product.builder().name("Smartwatch 2").description("Track your fitness, heart rate, and receive notifications on the go.").price(new BigDecimal("249.50")).imageUrl("https://i.imgur.com/s38i8p9.jpeg").build();
            Product p4 = Product.builder().name("Laptop Pro").description("A powerful and lightweight laptop for professionals and creatives.").price(new BigDecimal("1299.00")).imageUrl("https://i.imgur.com/p8gG1zE.jpeg").build();

            productRepository.saveAll(List.of(p1, p2, p3, p4));
            System.out.println("--- Database has been seeded with 4 sample products. ---");
        }

        // Seed admin user
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User adminUser = User.builder()
                    .name("Admin User")
                    .email("admin@example.com")
                    .passwordHash(passwordEncoder.encode("admin"))
                    .role("ROLE_ADMIN")
                    .build();
            userRepository.save(adminUser);
            System.out.println("--- Admin user created successfully! ---");
        }
    }
}