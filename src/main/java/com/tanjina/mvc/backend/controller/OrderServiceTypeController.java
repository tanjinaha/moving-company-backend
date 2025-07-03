package com.tanjina.mvc.backend.controller;

import com.tanjina.mvc.backend.entity.OrderServiceType;
import com.tanjina.mvc.backend.service.OrderServiceTypeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrderServiceType entities.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/orderservicetypes")
public class OrderServiceTypeController {

    private final OrderServiceTypeService orderServiceTypeService;

    /**
     * Constructor injection of the service.
     */
    public OrderServiceTypeController(OrderServiceTypeService orderServiceTypeService) {
        this.orderServiceTypeService = orderServiceTypeService;
    }

    /**
     * POST /orderservicetypes
     * Creates a new OrderServiceType.
     */
    @PostMapping
    public ResponseEntity<OrderServiceType> addOrderServiceType(@RequestBody OrderServiceType orderServiceType) {
        OrderServiceType saved = orderServiceTypeService.addOrderServiceType(orderServiceType);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * GET /orderservicetypes
     * Retrieves all OrderServiceTypes.
     */
    @GetMapping
    public ResponseEntity<List<OrderServiceType>> getAllOrderServiceTypes() {
        List<OrderServiceType> list = orderServiceTypeService.getAllOrderServiceTypes();
        return ResponseEntity.ok(list);
    }

    /**
     * GET /orderservicetypes/{id}
     * Retrieves an OrderServiceType by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderServiceType> getOrderServiceTypeById(@PathVariable Integer id) {
        Optional<OrderServiceType> opt = orderServiceTypeService.getOrderServiceTypeById(id);
        return opt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * PUT /orderservicetypes
     * Updates an existing OrderServiceType.
     */
    @PutMapping
    public ResponseEntity<OrderServiceType> updateOrderServiceType(@RequestBody OrderServiceType orderServiceType) {
        OrderServiceType updated = orderServiceTypeService.updateOrderServiceType(orderServiceType);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /orderservicetypes/{id}
     * Deletes an OrderServiceType by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderServiceType(@PathVariable Integer id) {
        orderServiceTypeService.deleteOrderServiceType(id);
        return ResponseEntity.noContent().build();
    }
}