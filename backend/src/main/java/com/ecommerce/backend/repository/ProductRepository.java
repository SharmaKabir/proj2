// package com.ecommerce.backend.repository;

// import com.ecommerce.backend.model.Product;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import java.util.List;
// @Repository
// public interface ProductRepository extends JpaRepository<Product, Long> {
//     List<Product> findByNameContainingIgnoreCase(String name);
// }
package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds products by category name, ignoring case.
     * The method name is parsed by Spring Data JPA to create the query:
     * "select p from Product p where upper(p.category.name) = upper(?1)"
     * This is the correct way to query based on a nested object's property.
     */
    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    /**
     * Finds products whose name contains the given search term, ignoring case.
     */
    List<Product> findByNameContainingIgnoreCase(String name);
}