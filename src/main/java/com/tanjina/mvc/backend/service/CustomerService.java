package com.tanjina.mvc.backend.service;  // This tells the computer where this file is located in your project

// We need to bring in (import) the Customer class and the CustomerRepository so we can use them here
import com.tanjina.mvc.backend.entity.Customer;
import com.tanjina.mvc.backend.repository.CustomerRepository;

// These are tools from Spring Boot that help us build a service class
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This word tells Spring Boot: "This file contains business logic"
@Service
public class CustomerService {

    // This line connects us to the part of the program that saves data to the database
    private final CustomerRepository customerRepository;

    // This is the constructor. When the program runs, it gives us a ready-to-use connection to the database.
    // It's like saying: "Please give me the tool that knows how to save and find customers."
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //  1. ADD a new customer and save their info in the database
    public Customer addCustomer(Customer customer) {
        // This line actually saves the customer in the database
        return customerRepository.save(customer);
    }

    //  2. GET a list of all customers from the database
    public List<Customer> getAllCustomers() {
        // This returns all rows from the "customers" table
        return customerRepository.findAll();
    }

    //  3. GET ONE customer by their ID (like customer #1, #2, etc.)
    public Optional<Customer> getCustomerById(Integer id) {
        // This tries to find one customer with the matching ID
        return customerRepository.findById(id);
    }

    //  4. DELETE a customer by their ID
    public void deleteCustomer(Integer id) {
        // This deletes the customer from the database if the ID exists
        customerRepository.deleteById(id);
    }

    //  5. UPDATE an existing customer (like changing their phone or email)
    public Customer updateCustomer(Customer customer) {
        // This will update the customer if the ID exists, or create a new one if it doesn’t
        return customerRepository.save(customer);
    }
}