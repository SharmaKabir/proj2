package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}