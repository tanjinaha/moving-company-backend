package com.tanjina.mvc.backend.controller;  // This defines the location of the file in your project

// ✅ Import the correct classes
import com.tanjina.mvc.backend.entity.Consultant;
import com.tanjina.mvc.backend.service.ConsultantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/consultants")  // ✅ Endpoint now matches renamed table
public class ConsultantController {

    // ✅ Connect to the service layer
    private final ConsultantService consultantService;

    // ✅ Constructor to inject the service
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // ✅ POST: Add a new consultant
    @PostMapping
    public ResponseEntity<Consultant> addConsultant(@RequestBody Consultant consultant) {
        Consultant savedConsultant = consultantService.addConsultant(consultant);
        return ResponseEntity.status(201).body(savedConsultant);
    }

    // ✅ GET: Get all consultants
    @GetMapping
    public ResponseEntity<List<Consultant>> getAllConsultants() {
        List<Consultant> consultants = consultantService.getAllConsultants();
        return ResponseEntity.ok(consultants);
    }

    // ✅ GET by ID: Find one consultant
    @GetMapping("/{id}")
    public ResponseEntity<Consultant> getConsultantById(@PathVariable Integer id) {
        Optional<Consultant> consultant = consultantService.getConsultantById(id);
        return consultant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ PUT: Update consultant info
    @PutMapping
    public ResponseEntity<Consultant> updateConsultant(@RequestBody Consultant consultant) {
        Consultant updated = consultantService.updateConsultant(consultant);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE: Remove a consultant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultant(@PathVariable Integer id) {
        consultantService.deleteConsultant(id);
        return ResponseEntity.noContent().build();
    }
}
