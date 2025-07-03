package com.tanjina.mvc.backend.controller;  // Location of this controller class

import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController tells Spring Boot this class will handle HTTP requests and send JSON responses
@RestController

// Base URL for all endpoints in this controller will start with /orders
@RequestMapping("/orders")
public class OrderController {

    // Reference to the OrderService to handle business logic
    private final OrderService orderService;

    // Constructor Injection of the service
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /orders
    // Add a new order to the database
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order savedOrder = orderService.addOrder(order);
        return ResponseEntity.status(201).body(savedOrder);  // HTTP 201 Created
    }

    // GET /orders
    // Retrieve all orders from the database
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);  // HTTP 200 OK
    }

    // GET /orders/{id}
    // Retrieve a specific order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());  // HTTP 404 Not Found if not found
    }

    // PUT /orders
    // Update an existing order
    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);  // HTTP 200 OK
    }

    // DELETE /orders/{id}
    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content
    }
}