// package com.ecommerce.backend.controller;

// import com.ecommerce.backend.dto.OrderRequest;
// import com.ecommerce.backend.model.Order;
// import com.ecommerce.backend.model.OrderItem;
// import com.ecommerce.backend.model.Product;
// import com.ecommerce.backend.model.User;
// import com.ecommerce.backend.repository.OrderRepository;
// import com.ecommerce.backend.repository.ProductRepository;
// import com.ecommerce.backend.repository.UserRepository;

// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.core.userdetails.UserDetails;
// import com.ecommerce.backend.repository.UserRepository;
// import java.math.BigDecimal;
// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/orders")
// @RequiredArgsConstructor
// public class OrderController {

//     private final OrderRepository orderRepository;
//     private final ProductRepository productRepository;
//     private final UserRepository userRepository;
//     @PostMapping
//     public ResponseEntity<Order> placeOrder(
//             @AuthenticationPrincipal User user,
//             @RequestBody OrderRequest orderRequest) {

//         // Build the Order entity from the request
//         Order order = Order.builder()
//                 .user(user)
//                 .shippingFullName(orderRequest.getShippingAddress().getFullName())
//                 .shippingAddressLine1(orderRequest.getShippingAddress().getAddressLine1())
//                 .shippingCity(orderRequest.getShippingAddress().getCity())
//                 .shippingPostalCode(orderRequest.getShippingAddress().getPostalCode())
//                 .shippingCountry(orderRequest.getShippingAddress().getCountry())
//                 .build();

//         // Create OrderItems and calculate total
//         List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemDto -> {
//             Product product = productRepository.findById(itemDto.getProductId())
//                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + itemDto.getProductId()));
//             return OrderItem.builder()
//                     .product(product)
//                     .order(order)
//                     .quantity(itemDto.getQuantity())
//                     .priceAtPurchase(product.getPrice())
//                     .build();
//         }).collect(Collectors.toList());

//         order.setOrderItems(orderItems);
//         BigDecimal total = orderItems.stream()
//                 .map(item -> item.getPriceAtPurchase().multiply(new BigDecimal(item.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//         order.setTotalAmount(total);

//         Order savedOrder = orderRepository.save(order);
//         return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
//     }

//     @GetMapping("/my-orders")
//     public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal User user) {
//         List<Order> orders = orderRepository.findByUser_IdOrderByOrderDateDesc(user.getId());
//         return ResponseEntity.ok(orders);
//     }
// }
package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.OrderRequest;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.OrderItem;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

//     @PostMapping
// public ResponseEntity<Order> placeOrder(
//         @AuthenticationPrincipal UserDetails userDetails,
//         @RequestBody OrderRequest orderRequest) {

//     User user = userRepository.findByEmail(userDetails.getUsername())
//             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

//     // Combine shipping fields into one string
//     String shippingAddress = orderRequest.getShippingAddress().getFullName() + ", "
//             + orderRequest.getShippingAddress().getAddressLine1() + ", "
//             + orderRequest.getShippingAddress().getCity() + ", "
//             + orderRequest.getShippingAddress().getPostalCode() + ", "
//             + orderRequest.getShippingAddress().getCountry();

//     Order order = Order.builder()
//             .user(user)
//             .orderDate(new java.sql.Timestamp(System.currentTimeMillis()))
//             .status("PENDING")
//             .shippingAddress(shippingAddress)
//             .build();

//     // Create OrderItems and calculate total
//     List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemDto -> {
//         Product product = productRepository.findById(itemDto.getProductId())
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + itemDto.getProductId()));
//         return OrderItem.builder()
//                 .product(product)
//                 .order(order)
//                 .quantity(itemDto.getQuantity())
//                 .priceAtPurchase(product.getPrice())
//                 .build();
//     }).collect(Collectors.toList());

//     order.setOrderItems(orderItems);
//     BigDecimal total = orderItems.stream()
//             .map(item -> item.getPriceAtPurchase().multiply(new BigDecimal(item.getQuantity())))
//             .reduce(BigDecimal.ZERO, BigDecimal::add);
//     order.setTotalAmount(total);

//     Order savedOrder = orderRepository.save(order);
//     return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
// }
@PostMapping
    public ResponseEntity<Order> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody OrderRequest orderRequest) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // --- STOCK VALIDATION & PREPARATION ---
        // 1. Create a list to hold the products we need to update.
        List<Product> productsToUpdate = new ArrayList<>();
        // 2. Loop through items in the cart to validate stock.
        for (OrderRequest.CartItemDto itemDto : orderRequest.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + itemDto.getProductId()));

            // 3. Check if enough stock exists.
            if (product.getStockQuantity() < itemDto.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock for " + product.getName() + ". Available: " + product.getStockQuantity() + ", Requested: " + itemDto.getQuantity());
            }
            // 4. If stock is sufficient, update the quantity and add to our list.
            product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity());
            productsToUpdate.add(product);
        }
        // --- END OF VALIDATION ---


        // Build the Order entity from the request
        Order order = Order.builder()
                .user(user)
                .shippingFullName(orderRequest.getShippingAddress().getFullName())
                .shippingAddressLine1(orderRequest.getShippingAddress().getAddressLine1())
                .shippingCity(orderRequest.getShippingAddress().getCity())
                .shippingPostalCode(orderRequest.getShippingAddress().getPostalCode())
                .shippingCountry(orderRequest.getShippingAddress().getCountry())
                .status("PENDING")
                .orderDate(new java.sql.Timestamp(System.currentTimeMillis()))
                .build();

        // Create OrderItems and calculate total
        List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId()).get(); // We can use .get() here because we already validated it
            return OrderItem.builder()
                    .product(product)
                    .order(order)
                    .quantity(itemDto.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        BigDecimal total = orderItems.stream()
                .map(item -> item.getPriceAtPurchase().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        // --- SAVE EVERYTHING ---
        // 5. Save the products with their new, decremented stock counts.
        productRepository.saveAll(productsToUpdate);
        // 6. Save the final order.
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        List<Order> orders = orderRepository.findByUser_IdOrderByOrderDateDesc(user.getId());
        return ResponseEntity.ok(orders);
    }
}