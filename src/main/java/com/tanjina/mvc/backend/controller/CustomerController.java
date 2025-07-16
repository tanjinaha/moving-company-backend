package com.tanjina.mvc.backend.controller;

import com.tanjina.mvc.backend.entity.Customer;
import com.tanjina.mvc.backend.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// ✅ Allow frontend (http://localhost:5173) to call this backend (CORS policy)
@CrossOrigin(origins = "http://localhost:5173")

// ✅ This is a REST controller: handles HTTP requests and returns JSON
@RestController

// ✅ Base URL for all methods below will be /customers
@RequestMapping("/customers")
public class CustomerController {

    // ✅ Connects to CustomerService (business logic)
    private final CustomerService customerService;

    // ✅ Constructor injection (Spring Boot recommended)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ POST /customers — create a new customer
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(201).body(savedCustomer);
    }

    // ✅ GET /customers — get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // ✅ GET /customers/{id} — get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ PUT /customers — update an existing customer
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // ✅ DELETE /customers/{id} — delete customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ GET /customers/search?name=Ali — search customers by name
    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomersByName(@RequestParam String name) {
        List<Customer> result = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(result);
    }
}
