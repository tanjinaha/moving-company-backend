package com.tanjina.mvc.backend.controller;  // Location of this controller class

import com.tanjina.mvc.backend.dto.OrderDetailsDTO;
import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/details")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails() {
        List<OrderDetailsDTO> details = orderService.getOrderDetails();
        return ResponseEntity.ok(details);
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

    /// ✅ This version will match PUT /orders/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Integer id,
            @RequestBody Order order) {

        // Step 1: Check if order exists
        Optional<Order> optionalOrder = orderService.getOrderById(id);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found if order doesn't exist
        }

        // Step 2: Get existing order and update only the note
        Order existingOrder = optionalOrder.get();
        existingOrder.setNote(order.getNote());

        // Step 3: Save updated order
        Order updatedOrder = orderService.updateOrder(existingOrder);
        return ResponseEntity.ok(updatedOrder); // 200 OK with updated order
    }



    // DELETE /orders/{id}
    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content
    }
}