package com.tanjina.mvc.backend.service;  // Package location for this service class

import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that contains business logic for managing Orders.
 * This class uses the OrderRepository to perform database operations.
 */
@Service  // Marks this class as a Spring-managed service component
public class OrderService {

    // Repository for accessing Order data in the database
    private final OrderRepository orderRepository;

    /**
     * Constructor injection: Spring provides the OrderRepository instance.
     * This promotes loose coupling and easy testing.
     */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Adds a new Order to the database.
     *
     * @param order The Order entity to save
     * @return The saved Order, including generated ID
     */
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Retrieves all Orders from the database.
     *
     * @return List of all Orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves a specific Order by its ID.
     *
     * @param id The Order ID to search for
     * @return An Optional containing the Order if found, or empty if not
     */
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    /**
     * Updates an existing Order.
     * If the Order ID exists, updates the record; otherwise, creates a new one.
     *
     * @param order The Order entity with updated information
     * @return The saved Order entity
     */
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Deletes an Order by its ID.
     *
     * @param id The ID of the Order to delete
     */
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}