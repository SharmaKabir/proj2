// package com.ecommerce.backend.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import java.math.BigDecimal;

// @Entity
// @Table(name = "order_items")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class OrderItem {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "order_id", nullable = false)
//     private Order order;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     private Product product;

//     private int quantity;

//     @Column(name = "price_at_purchase")
//     private BigDecimal priceAtPurchase;
// }
package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Column(name = "price_at_purchase")
    private BigDecimal priceAtPurchase;
}