package com.tanjina.mvc.backend.repository;  // This defines the package location

import com.tanjina.mvc.backend.entity.Order;  // Import the Order entity

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Order entities.
 *
 * Extends JpaRepository to provide built-in CRUD operations like save, findById, findAll, deleteById.
 * This allows easy interaction with the 'orders' table without writing SQL queries.
 */
@Repository  // Marks this interface as a Spring Data repository bean
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // You can add custom query methods here if needed, for example:
    // List<Order> findByCustomerId(Integer customerId);
    // List<Order> findByConsultantId(Integer consultantId);
}