// // package com.ecommerce.backend.controller;

// // import com.ecommerce.backend.model.Product;
// // import com.ecommerce.backend.service.ProductService;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;
// // import java.util.List;

// // @RestController
// // @RequestMapping("/api/products")
// // public class ProductController {
// //     private final ProductService productService;
// //     public ProductController(ProductService productService) {
// //         this.productService = productService;
// //     }
// //     @GetMapping
// //     public List<Product> getAllProducts() {
// //         return productService.getAllProducts();
// //     }
// //     @GetMapping("/{id}")
// //     public ResponseEntity<Product> getProductById(@PathVariable Long id) {
// //         return productService.getProductById(id)
// //                 .map(ResponseEntity::ok)
// //                 .orElse(ResponseEntity.notFound().build());
// //     }
// // }

// package com.ecommerce.backend.controller;

// import com.ecommerce.backend.model.Product;
// import com.ecommerce.backend.repository.ProductRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/products")
// @RequiredArgsConstructor
// public class ProductController {

//     private final ProductRepository productRepository;

//     @GetMapping
//     public ResponseEntity<List<Product>> getAllProducts() {
//         return ResponseEntity.ok(productRepository.findAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//         Optional<Product> product = productRepository.findById(id);
//         return product.map(ResponseEntity::ok)
//                       .orElse(ResponseEntity.notFound().build());
//     }
//     @GetMapping("/search")
//     public ResponseEntity<List<Product>> searchProducts(@RequestParam("q") String query) {
//         List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
//         return ResponseEntity.ok(products);
//     }
// }

package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        // Use the new, correct method from the repository
        List<Product> products = productRepository.findByCategory_NameIgnoreCase(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(products);
    }
}