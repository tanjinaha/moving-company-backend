package com.tanjina.mvc.backend.service;  // This tells the computer where this file is located in your project

// We need to bring in (import) the SalesConsultant class and the SalesConsultantRepository so we can use them here
import com.tanjina.mvc.backend.entity.SalesConsultant;
import com.tanjina.mvc.backend.repository.SalesConsultantRepository;

// These are tools from Spring Boot that help us build a service class
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This word tells Spring Boot: "This file contains business logic"
@Service
public class SalesConsultantService {

    // This line connects us to the part of the program that saves data to the database
    private final SalesConsultantRepository salesConsultantRepository;

    // This is the constructor. When the program runs, it gives us a ready-to-use connection to the database.
    // It's like saying: "Please give me the tool that knows how to save and find sales consultants."
    public SalesConsultantService(SalesConsultantRepository salesConsultantRepository) {
        this.salesConsultantRepository = salesConsultantRepository;
    }

    // 1. ADD a new sales consultant and save their info in the database
    public SalesConsultant addSalesConsultant(SalesConsultant salesConsultant) {
        // This line actually saves the sales consultant in the database
        return salesConsultantRepository.save(salesConsultant);
    }

    // 2. GET a list of all sales consultants from the database
    public List<SalesConsultant> getAllSalesConsultants() {
        // This returns all rows from the "salesConsultants" table
        return salesConsultantRepository.findAll();
    }

    // 3. GET ONE sales consultant by their ID (like consultant #1, #2, etc.)
    public Optional<SalesConsultant> getSalesConsultantById(Integer id) {
        // This tries to find one sales consultant with the matching ID
        return salesConsultantRepository.findById(id);
    }

    // 4. DELETE a sales consultant by their ID
    public void deleteSalesConsultant(Integer id) {
        // This deletes the sales consultant from the database if the ID exists
        salesConsultantRepository.deleteById(id);
    }

    // 5. UPDATE an existing sales consultant (like changing their email or phone)
    public SalesConsultant updateSalesConsultant(SalesConsultant salesConsultant) {
        // This will update the sales consultant if the ID exists, or create a new one if it doesn’t
        return salesConsultantRepository.save(salesConsultant);
    }
}