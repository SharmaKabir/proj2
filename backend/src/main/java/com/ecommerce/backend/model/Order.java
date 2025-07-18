// // package com.ecommerce.backend.model;

// // import jakarta.persistence.*;
// // import lombok.AllArgsConstructor;
// // import lombok.Builder;
// // import lombok.Data;
// // import lombok.NoArgsConstructor;
// // import java.math.BigDecimal;
// // import java.time.LocalDateTime;
// // import java.util.ArrayList;
// // import java.util.List;
// // import com.fasterxml.jackson.annotation.JsonManagedReference;
// // @Entity
// // @Table(name = "orders")
// // @Data
// // @Builder
// // @NoArgsConstructor
// // @AllArgsConstructor
// // public class Order {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @ManyToOne
// //     @JoinColumn(name = "user_id", nullable = false)
// //     private User user;

// //     @Column(name = "order_date")
// //     private java.sql.Timestamp orderDate;

// //     @Column(name = "status")
// //     private String status; // You can use String or an Enum

// //     @Column(name = "total_amount")
// //     private BigDecimal totalAmount;

// //     @Column(name = "shipping_address")
// //     private String shippingAddress;

// //     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
// //     @Builder.Default
// //     private List<OrderItem> orderItems = new ArrayList<>();
// // }

// package com.ecommerce.backend.model;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.List;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// @Entity
// @Table(name = "orders")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @Column(name = "order_date")
//     private java.sql.Timestamp orderDate;

//     @Column(name = "status")
//     private String status;

//     @Column(name = "total_amount")
//     private BigDecimal totalAmount;

//     @Column(name = "shipping_address")
//     private String shippingAddress;

//     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//     @Builder.Default
//     @JsonManagedReference
//     private List<OrderItem> orderItems = new ArrayList<>();
// }
package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date")
    private java.sql.Timestamp orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    // --- REPLACED single shippingAddress with structured fields ---
    @Column(name = "shipping_full_name")
    private String shippingFullName;

    @Column(name = "shipping_address_line_1")
    private String shippingAddressLine1;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_postal_code")
    private String shippingPostalCode;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
}