package com.tanjina.mvc.backend.controller;  // This defines the location of the file in your project (package structure)

// Importing necessary classes from your project and Spring Boot
import com.tanjina.mvc.backend.entity.SalesConsultant;
import com.tanjina.mvc.backend.service.SalesConsultantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
// @RestController tells Spring Boot: "This class handles API/web requests."
// It will not return HTML pages — it returns data (like JSON).
@RestController

// @RequestMapping sets the base URL for all methods in this controller.
// This means all endpoints will start with "/salesConsultants"
@RequestMapping("/salesconsultants")
public class SalesConsultantController {

    // This is a reference to our SalesConsultantService class.
    // We use it to access business logic (like saving or getting sales consultants)
    private final SalesConsultantService salesConsultantService;

    // Constructor Injection (recommended way in Spring Boot)
    // This constructor gives this controller access to the service layer
    public SalesConsultantController(SalesConsultantService salesConsultantService) {
        this.salesConsultantService = salesConsultantService;
    }

    // -----------------------------------------------------------------------
    // POST /salesConsultants
    // This method adds a new sales consultant to the database.
    // @PostMapping handles POST requests to the URL /salesConsultants
    // @RequestBody tells Spring: "Get the sales consultant data from the request body"
    @PostMapping
    public ResponseEntity<SalesConsultant> addSalesConsultant(@RequestBody SalesConsultant salesConsultant) {
        SalesConsultant savedConsultant = salesConsultantService.addSalesConsultant(salesConsultant);  // Save using service layer
        return ResponseEntity.status(201).body(savedConsultant);  // Return response with status code 201 (Created)
    }

    // -----------------------------------------------------------------------
    // GET /salesConsultants
    // This method returns a list of all sales consultants in the database
    // @GetMapping handles GET requests to the URL /salesConsultants
    @GetMapping
    public ResponseEntity<List<SalesConsultant>> getAllSalesConsultants() {
        List<SalesConsultant> consultants = salesConsultantService.getAllSalesConsultants();  // Get all sales consultants from service
        return ResponseEntity.ok(consultants);  // Return response with status code 200 (OK)
    }

    // -----------------------------------------------------------------------
    // GET /salesConsultants/{id}
    // This method returns a specific sales consultant by their ID
    // @PathVariable tells Spring: "Use the number in the URL as the sales consultant ID"
    @GetMapping("/{id}")
    public ResponseEntity<SalesConsultant> getSalesConsultantById(@PathVariable Integer id) {
        Optional<SalesConsultant> consultant = salesConsultantService.getSalesConsultantById(id);  // Try to find the sales consultant

        // If sales consultant is found, return it with 200 OK. If not, return 404 Not Found.
        return consultant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -----------------------------------------------------------------------
    // PUT /salesConsultants
    // This method updates an existing sales consultant in the database
    // @PutMapping handles PUT requests (used for updates)
    @PutMapping
    public ResponseEntity<SalesConsultant> updateSalesConsultant(@RequestBody SalesConsultant salesConsultant) {
        SalesConsultant updatedConsultant = salesConsultantService.updateSalesConsultant(salesConsultant);  // Update using service
        return ResponseEntity.ok(updatedConsultant);  // Return updated sales consultant with 200 OK
    }

    // -----------------------------------------------------------------------
    // DELETE /salesConsultants/{id}
    // This method deletes a sales consultant by their ID
    // @DeleteMapping handles DELETE requests to the URL /salesConsultants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesConsultant(@PathVariable Integer id) {
        salesConsultantService.deleteSalesConsultant(id);  // Ask the service to delete by ID
        return ResponseEntity.noContent().build();  // Return 204 No Content (success but nothing to return)
    }
}