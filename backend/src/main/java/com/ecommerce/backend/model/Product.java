// // package com.ecommerce.backend.model;

// // import jakarta.persistence.*;
// // import lombok.Data;
// // import java.math.BigDecimal;

// // @Data
// // @Entity
// // @Table(name = "products")
// // public class Product {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;
// //     private String name;
// //     private String description;
// //     private BigDecimal price;
// //     @Column(name = "stock_quantity")
// //     private int stockQuantity;
// //     private String imageUrl;

// //     @ManyToOne
// //     @JoinColumn(name = "category_id")
// //     private Category category;
// // }

// package com.ecommerce.backend.model;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.math.BigDecimal;

// @Data
// @Builder // Add this annotation
// @NoArgsConstructor // Add this annotation
// @AllArgsConstructor // Add this annotation
// @Entity
// @Table(name = "products")
// public class Product {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String name;
//     private String description;
//     private BigDecimal price;
//     private String imageUrl; // Add this field

//     @ManyToOne
//     @JoinColumn(name = "category_id")
//     private Category category;
// }
package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp; // Import this

import java.math.BigDecimal;
import java.time.LocalDateTime; // Import this

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // --- NEW FIELDS ---
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}