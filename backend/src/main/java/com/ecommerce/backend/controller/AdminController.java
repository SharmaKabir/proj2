// // // package com.ecommerce.backend.controller;

// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.web.bind.annotation.GetMapping;
// // // import org.springframework.web.bind.annotation.RequestMapping;
// // // import org.springframework.web.bind.annotation.RestController;

// // // @RestController
// // // @RequestMapping("/api/admin")
// // // public class AdminController {

// // //     @GetMapping("/test")
// // //     public ResponseEntity<String> testAdminAccess() {
// // //         return ResponseEntity.ok("Admin access is working!");
// // //     }
// // // }
// // package com.ecommerce.backend.controller;

// // import com.ecommerce.backend.model.Order;
// // import com.ecommerce.backend.repository.OrderRepository;
// // import lombok.RequiredArgsConstructor;

// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;

// // import com.ecommerce.backend.model.Product;
// // import com.ecommerce.backend.repository.ProductRepository;
// // import org.springframework.web.bind.annotation.*;
// // import org.springframework.web.server.ResponseStatusException;

// // import java.util.List;

// // @RestController
// // @RequestMapping("/api/admin")
// // @RequiredArgsConstructor
// // public class AdminController {

// //     private final OrderRepository orderRepository;
// //     private final ProductRepository productRepository;

// //     @GetMapping("/test")
// //     public ResponseEntity<String> testAdminAccess() {
// //         return ResponseEntity.ok("Admin access is working!");
// //     }

// //     // New endpoint to get all orders from all users
// //     @GetMapping("/orders")
// //     public ResponseEntity<List<Order>> getAllOrders() {
// //         List<Order> allOrders = orderRepository.findAll();
// //         return ResponseEntity.ok(allOrders);
// //     }
// //     @PostMapping("/products")
// //     public ResponseEntity<Product> addProduct(@RequestBody Product product) {
// //         Product savedProduct = productRepository.save(product);
// //         return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
// //     }

// //     @PutMapping("/products/{id}")
// //     public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
// //         Product product = productRepository.findById(id)
// //                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));

// //         product.setName(productDetails.getName());
// //         product.setDescription(productDetails.getDescription());
// //         product.setPrice(productDetails.getPrice());
// //         product.setImageUrl(productDetails.getImageUrl());
// //         // Add category update if needed
// //         // product.setCategory(productDetails.getCategory());

// //         final Product updatedProduct = productRepository.save(product);
// //         return ResponseEntity.ok(updatedProduct);
// //     }

// //     @DeleteMapping("/products/{id}")
// //     public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
// //         if (!productRepository.existsById(id)) {
// //             return ResponseEntity.notFound().build();
// //         }
// //         productRepository.deleteById(id);
// //         return ResponseEntity.noContent().build();
// //     }
// // }
// package com.ecommerce.backend.controller;

// import com.ecommerce.backend.dto.ProductRequest;
// import com.ecommerce.backend.model.Category;
// import com.ecommerce.backend.model.Order;
// import com.ecommerce.backend.model.Product;
// import com.ecommerce.backend.repository.CategoryRepository;
// import com.ecommerce.backend.repository.OrderRepository;
// import com.ecommerce.backend.repository.ProductRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;

// import java.util.List;

// @RestController
// @RequestMapping("/api/admin")
// @RequiredArgsConstructor
// public class AdminController {

//     private final OrderRepository orderRepository;
//     private final ProductRepository productRepository;
//     private final CategoryRepository categoryRepository; // Inject CategoryRepository

//     @GetMapping("/test")
//     public ResponseEntity<String> testAdminAccess() {
//         return ResponseEntity.ok("Admin access is working!");
//     }

//     @GetMapping("/orders")
//     public ResponseEntity<List<Order>> getAllOrders() {
//         List<Order> allOrders = orderRepository.findAll();
//         return ResponseEntity.ok(allOrders);
//     }

//     @PostMapping("/products")
//     public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
//         Category category = categoryRepository.findById(productRequest.getCategoryId())
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

//         Product newProduct = Product.builder()
//                 .name(productRequest.getName())
//                 .description(productRequest.getDescription())
//                 .price(productRequest.getPrice())
//                 .imageUrl(productRequest.getImageUrl())
//                 .category(category)
//                 .build();

//         Product savedProduct = productRepository.save(newProduct);
//         return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
//     }

//     @PutMapping("/products/{id}")
//     public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
//         Product product = productRepository.findById(id)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));

//         Category category = categoryRepository.findById(productRequest.getCategoryId())
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

//         product.setName(productRequest.getName());
//         product.setDescription(productRequest.getDescription());
//         product.setPrice(productRequest.getPrice());
//         product.setImageUrl(productRequest.getImageUrl());
//         product.setCategory(category); // Update the category

//         final Product updatedProduct = productRepository.save(product);
//         return ResponseEntity.ok(updatedProduct);
//     }

//     @DeleteMapping("/products/{id}")
//     public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//         if (!productRepository.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         productRepository.deleteById(id);
//         return ResponseEntity.noContent().build();
//     }
// }   @PostMapping("/categories")
//     public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
//         Category newCategory = Category.builder()
//                 .name(categoryRequest.getName())
//                 .description(categoryRequest.getDescription())
//                 .build();
//         Category savedCategory = categoryRepository.save(newCategory);
//         return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//     }

//     @PutMapping("/categories/{id}")
//     public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
//         Category category = categoryRepository.findById(id)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

//         category.setName(categoryRequest.getName());
//         category.setDescription(categoryRequest.getDescription());

//         Category updatedCategory = categoryRepository.save(category);
//         return ResponseEntity.ok(updatedCategory);
//     }

//     @DeleteMapping("/categories/{id}")
//     public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//         if (!categoryRepository.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         // Note: This is a simple delete. In a real app, you might need to handle products within this category.
//         categoryRepository.deleteById(id);
//         return ResponseEntity.noContent().build();
//     }
package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.CategoryRequest; // Import the new DTO
import com.ecommerce.backend.dto.ProductRequest;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/test")
    public ResponseEntity<String> testAdminAccess() {
        return ResponseEntity.ok("Admin access is working!");
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return ResponseEntity.ok(allOrders);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Product newProduct = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .imageUrl(productRequest.getImageUrl())
                .category(category)
                .build();

        Product savedProduct = productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setCategory(category);

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- CATEGORY MANAGEMENT ENDPOINTS ---
    // These methods are now correctly placed INSIDE the AdminController class

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category newCategory = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        Category savedCategory = categoryRepository.save(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

} // <-- This is the final closing brace of the AdminController class