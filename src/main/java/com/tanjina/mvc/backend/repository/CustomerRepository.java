package com.tanjina.mvc.backend.repository;

// ✅ Import the Customer entity
import com.tanjina.mvc.backend.entity.Customer;

// ✅ Spring Data JPA tools
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// ✅ This tells Spring to treat this as a repository
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // ✅ Custom method to search by name (used in CustomerService)
    List<Customer> findByCustomerNameContainingIgnoreCase(String name);


    // ✅ Custom method to find a customer using phone and email
    Optional<Customer> findByCustomerPhoneAndCustomerEmail(Long customerPhone, String customerEmail);

    // ✅ You also automatically get:
    // - save()         → to save a customer
    // - findAll()      → to list all customers
    // - findById()     → to find one by ID
    // - deleteById()   → to delete by ID
}
