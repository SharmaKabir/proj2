// package com.ecommerce.backend.controller;

// import com.ecommerce.backend.model.Category;
// import com.ecommerce.backend.service.CategoryService;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/categories")
// public class CategoryController {
//     private final CategoryService categoryService;
//     public CategoryController(CategoryService categoryService) {
//         this.categoryService = categoryService;
//     }
//     @GetMapping
//     public List<Category> getAllCategories() {
//         return categoryService.getAllCategories();
//     }
// }
package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.repository.CategoryRepository; // Import repository
import com.ecommerce.backend.service.CategoryService;
import lombok.RequiredArgsConstructor; // Import RequiredArgsConstructor
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor // Use this for constructor injection
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository; // Inject repository

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // New endpoint to get a single category by its ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }
}