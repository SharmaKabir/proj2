package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically creates this method from its name.
    Optional<User> findByEmail(String email);
}