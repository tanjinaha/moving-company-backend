package com.tanjina.mvc.backend.service;

import com.tanjina.mvc.backend.entity.OrderServiceType;
import com.tanjina.mvc.backend.repository.OrderServiceTypeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class containing business logic for OrderServiceType.
 * This class communicates with the repository to perform CRUD operations.
 */
@Service
public class OrderServiceTypeService {

    // Repository instance for database interactions
    private final OrderServiceTypeRepository orderServiceTypeRepository;

    /**
     * Constructor injection of the repository.
     * This promotes loose coupling and easy testing.
     */
    public OrderServiceTypeService(OrderServiceTypeRepository orderServiceTypeRepository) {
        this.orderServiceTypeRepository = orderServiceTypeRepository;
    }

    /**
     * Adds a new OrderServiceType entry to the database.
     *
     * @param orderServiceType The OrderServiceType entity to save.
     * @return The saved entity with generated ID.
     */
    public OrderServiceType addOrderServiceType(OrderServiceType orderServiceType) {
        return orderServiceTypeRepository.save(orderServiceType);
    }

    /**
     * Retrieves all OrderServiceType entries from the database.
     *
     * @return A list of all OrderServiceTypes.
     */
    public List<OrderServiceType> getAllOrderServiceTypes() {
        return orderServiceTypeRepository.findAll();
    }

    /**
     * Retrieves an OrderServiceType by its ID.
     *
     * @param id The ID of the OrderServiceType to retrieve.
     * @return An Optional containing the found entity or empty if not found.
     */
    public Optional<OrderServiceType> getOrderServiceTypeById(Integer id) {
        return orderServiceTypeRepository.findById(id);
    }

    /**
     * Updates an existing OrderServiceType entry.
     * If the ID exists, updates the record; otherwise creates a new one.
     *
     * @param orderServiceType The updated OrderServiceType entity.
     * @return The saved entity.
     */
    public OrderServiceType updateOrderServiceType(OrderServiceType orderServiceType) {
        return orderServiceTypeRepository.save(orderServiceType);
    }

    /**
     * Deletes an OrderServiceType by its ID.
     *
     * @param id The ID of the OrderServiceType to delete.
     */
    public void deleteOrderServiceType(Integer id) {
        orderServiceTypeRepository.deleteById(id);
    }
}
