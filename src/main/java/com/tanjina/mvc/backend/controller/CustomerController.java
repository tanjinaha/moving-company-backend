// This declares the package location of the class inside your project folders
package com.tanjina.mvc.backend.controller;

// Import all necessary libraries and annotations BEFORE usage

import org.springframework.web.bind.annotation.CrossOrigin;
import com.tanjina.mvc.backend.entity.Customer;
import com.tanjina.mvc.backend.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @CrossOrigin allows React frontend (running on port 3000) to access this backend API (running on port 8080)
// This solves browser security issues (CORS policy)
@CrossOrigin(origins = "http://localhost:5173")

// @RestController tells Spring Boot: "This class handles API/web requests."
// It will not return HTML pages — it returns data (like JSON).
@RestController

// @RequestMapping sets the base URL for all methods in this controller.
// This means all endpoints will start with "/customers"
@RequestMapping("/customers")
public class CustomerController
{

    // This is a reference to our CustomerService class.
    // We use it to access business logic (like saving or getting customers)
    private final CustomerService customerService;

    // Constructor Injection (recommended way in Spring Boot)
    // This constructor gives this controller access to the service layer
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    // -----------------------------------------------------------------------
    // POST /customers
    // This method adds a new customer to the database.
    // @PostMapping handles POST requests to the URL /customers
    // @RequestBody tells Spring: "Get the customer data from the request body"
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
    {
        Customer savedCustomer = customerService.addCustomer(customer);  // Save using service layer
        return ResponseEntity.status(201).body(savedCustomer);  // Return response with status code 201 (Created)
    }

    // -----------------------------------------------------------------------
    // GET /customers
    // This method returns a list of all customers in the database
    // @GetMapping handles GET requests to the URL /customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customers = customerService.getAllCustomers();  // Get all customers from service
        return ResponseEntity.ok(customers);  // Return response with status code 200 (OK)
    }

    // -----------------------------------------------------------------------
    // GET /customers/{id}
    // This method returns a specific customer by their ID
    // @PathVariable tells Spring: "Use the number in the URL as the customer ID"
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id)
    {
        Optional<Customer> customer = customerService.getCustomerById(id);  // Try to find the customer

        // If customer is found, return it with 200 OK. If not, return 404 Not Found.
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------------------------------------------------
    // PUT /customers
    // This method updates an existing customer in the database
    // @PutMapping handles PUT requests (used for updates)
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
    {
        Customer updatedCustomer = customerService.updateCustomer(customer);  // Update using service
        return ResponseEntity.ok(updatedCustomer);  // Return updated customer with 200 OK
    }

    // -----------------------------------------------------------------------
    // DELETE /customers/{id}
    // This method deletes a customer by their ID
    // @DeleteMapping handles DELETE requests to the URL /customers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id)
    {
        customerService.deleteCustomer(id);  // Ask the service to delete by ID
        return ResponseEntity.noContent().build();  // Return 204 No Content (success but nothing to return)
    }

    // -----------------------------------------------------------------------
    // GET /customers/search?name=mo
    // This lets the frontend search customers by name (like "mo" or "Ali")
    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomersByName(@RequestParam String name)
    {
        List<Customer> result = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(result);
    }


}