// package com.ecommerce.backend.repository;

// import com.ecommerce.backend.model.Category;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface CategoryRepository extends JpaRepository<Category, Long> {}
package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // <-- Import Optional

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Add this method to find a category by its name
    Optional<Category> findByName(String name);
}