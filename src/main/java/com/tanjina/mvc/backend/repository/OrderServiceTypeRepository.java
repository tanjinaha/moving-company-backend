package com.tanjina.mvc.backend.repository;

import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.entity.OrderServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for OrderServiceType entity.
 * Extends JpaRepository to get basic CRUD methods for free.
 *
 * This repository provides a method to find all OrderServiceType records linked to a specific Order.
 */
@Repository
public interface OrderServiceTypeRepository extends JpaRepository<OrderServiceType, Integer> {

    /**
     * Find all OrderServiceType records linked to a given Order.
     *
     * @param order The Order entity to find service types for.
     * @return List of OrderServiceType associated with the Order.
     */
    List<OrderServiceType> findAllByOrder(Order order);
}
