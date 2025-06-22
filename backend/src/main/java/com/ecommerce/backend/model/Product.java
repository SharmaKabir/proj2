package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}