// // package com.ecommerce.backend.config;

// // import com.ecommerce.backend.model.Product;
// // import com.ecommerce.backend.repository.ProductRepository;
// // import lombok.RequiredArgsConstructor;
// // import org.springframework.boot.CommandLineRunner;
// // import org.springframework.stereotype.Component;

// // import java.math.BigDecimal;
// // import java.util.List;

// // @Component
// // @RequiredArgsConstructor
// // public class DataInitializer implements CommandLineRunner {

// //     private final ProductRepository productRepository;

// //     @Override
// //     public void run(String... args) {
// //         // This check prevents seeding data every time you restart during a session
// //         if (productRepository.count() == 0) {
// //             Product p1 = Product.builder().name("Smartphone X").description("The latest model with a stunning display and AI-powered camera.").price(new BigDecimal("799.99")).imageUrl("https://i.imgur.com/l22aG2A.jpeg").build();
// //             Product p2 = Product.builder().name("Wireless Headphones").description("Noise-cancelling over-ear headphones with 30-hour battery life.").price(new BigDecimal("199.99")).imageUrl("https://i.imgur.com/nS0rC6O.jpeg").build();
// //             Product p3 = Product.builder().name("Smartwatch 2").description("Track your fitness, heart rate, and receive notifications on the go.").price(new BigDecimal("249.50")).imageUrl("https://i.imgur.com/s38i8p9.jpeg").build();
// //             Product p4 = Product.builder().name("Laptop Pro").description("A powerful and lightweight laptop for professionals and creatives.").price(new BigDecimal("1299.00")).imageUrl("https://i.imgur.com/p8gG1zE.jpeg").build();

// //             productRepository.saveAll(List.of(p1, p2, p3, p4));
// //             System.out.println("--- Database has been seeded with 4 sample products. ---");
// //         }
// //     }
// // }
// package com.ecommerce.backend.config;

// import com.ecommerce.backend.model.Product;
// import com.ecommerce.backend.model.User; // <-- Import User model
// import com.ecommerce.backend.repository.ProductRepository;
// import com.ecommerce.backend.repository.UserRepository; // <-- Import UserRepository
// import lombok.RequiredArgsConstructor;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder; // <-- Import PasswordEncoder
// import org.springframework.stereotype.Component;

// import java.math.BigDecimal;
// import java.util.List;

// @Component
// @RequiredArgsConstructor
// public class DataInitializer implements CommandLineRunner {

//     private final ProductRepository productRepository;
//     private final UserRepository userRepository; // <-- Add UserRepository
//     private final PasswordEncoder passwordEncoder; // <-- Add PasswordEncoder

//     @Override
//     public void run(String... args) {
//         // Seed products
//         if (productRepository.count() == 0) {
//             Product p1 = Product.builder().name("Smartphone X").description("The latest model with a stunning display and AI-powered camera.").price(new BigDecimal("799.99")).imageUrl("https://i.imgur.com/l22aG2A.jpeg").build();
//             Product p2 = Product.builder().name("Wireless Headphones").description("Noise-cancelling over-ear headphones with 30-hour battery life.").price(new BigDecimal("199.99")).imageUrl("https://i.imgur.com/nS0rC6O.jpeg").build();
//             Product p3 = Product.builder().name("Smartwatch 2").description("Track your fitness, heart rate, and receive notifications on the go.").price(new BigDecimal("249.50")).imageUrl("https://i.imgur.com/s38i8p9.jpeg").build();
//             Product p4 = Product.builder().name("Laptop Pro").description("A powerful and lightweight laptop for professionals and creatives.").price(new BigDecimal("1299.00")).imageUrl("https://i.imgur.com/p8gG1zE.jpeg").build();

//             productRepository.saveAll(List.of(p1, p2, p3, p4));
//             System.out.println("--- Database has been seeded with 4 sample products. ---");
//         }

//         // Seed admin user
//         if (userRepository.findByEmail("admin@example.com").isEmpty()) {
//             User adminUser = User.builder()
//                     .name("Admin User")
//                     .email("admin@example.com")
//                     .passwordHash(passwordEncoder.encode("admin"))
//                     .role("ROLE_ADMIN")
//                     .build();
//             userRepository.save(adminUser);
//             System.out.println("--- Admin user created successfully! ---");
//         }
//     }
// }
package com.ecommerce.backend.config;

import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        // Step 1: Seed categories if the table is empty
        if (categoryRepository.count() == 0) {
            Category c1 = Category.builder().name("Electronics").description("Gadgets and devices.").build();
            Category c2 = Category.builder().name("Smart Devices").description("Connected and intelligent devices.").build();
            categoryRepository.saveAll(List.of(c1, c2));
            System.out.println("--- Database has been seeded with 2 sample categories. ---");
        }

        // Step 2: Seed products if the table is empty
        if (productRepository.count() == 0) {
            // Fetch the categories we just created to ensure they are managed by JPA
            Optional<Category> electronicsOpt = categoryRepository.findByName("Electronics");
            Optional<Category> smartDevicesOpt = categoryRepository.findByName("Smart Devices");

            if (electronicsOpt.isPresent() && smartDevicesOpt.isPresent()) {
                Category electronics = electronicsOpt.get();
                Category smartDevices = smartDevicesOpt.get();

                Product p1 = Product.builder().name("Smartphone X").description("The latest model...").price(new BigDecimal("799.99")).imageUrl("https://i.imgur.com/l22aG2A.jpeg").category(electronics).build();
                Product p2 = Product.builder().name("Wireless Headphones").description("Noise-cancelling...").price(new BigDecimal("199.99")).imageUrl("https://i.imgur.com/nS0rC6O.jpeg").category(smartDevices).build();
                Product p3 = Product.builder().name("Smartwatch 2").description("Track your fitness...").price(new BigDecimal("249.50")).imageUrl("https://i.imgur.com/s38i8p9.jpeg").category(smartDevices).build();
                Product p4 = Product.builder().name("Laptop Pro").description("A powerful laptop...").price(new BigDecimal("1299.00")).imageUrl("https://i.imgur.com/p8gG1zE.jpeg").category(electronics).build();

                productRepository.saveAll(List.of(p1, p2, p3, p4));
                System.out.println("--- Database has been seeded with 4 sample products linked to categories. ---");
            } else {
                System.out.println("--- Could not seed products because categories were not found. ---");
            }
        }

        // Step 3: Seed admin user
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