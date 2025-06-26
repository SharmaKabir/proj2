package com.ecommerce.backend.dto;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String description;
}