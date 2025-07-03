package com.tanjina.mvc.backend.repository;  // Package location

import com.tanjina.mvc.backend.entity.OrderServiceType;  // Import entity

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for OrderServiceType entity.
 *
 * Extends JpaRepository to provide built-in CRUD operations on the orderservicetypes table.
 * No implementation needed; Spring Data JPA provides methods like save, findById, findAll, deleteById.
 */
@Repository
public interface OrderServiceTypeRepository extends JpaRepository<OrderServiceType, Integer> {

    // You can add custom query methods here if needed.
    // Example: List<OrderServiceType> findByOrderId(Integer orderId);
}