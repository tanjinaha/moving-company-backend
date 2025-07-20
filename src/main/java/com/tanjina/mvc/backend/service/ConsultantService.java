package com.tanjina.mvc.backend.service;  // This tells the computer where this file is located in your project

// ✅ Import your Consultant entity and ConsultantRepository
import com.tanjina.mvc.backend.entity.Consultant;
import com.tanjina.mvc.backend.repository.ConsultantRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ✅ Service class to handle business logic for consultants
 */
@Service
public class ConsultantService {

    // ✅ This connects to the ConsultantRepository (your database for consultants)
    private final ConsultantRepository consultantRepository;

    // ✅ Constructor to initialize the repository connection
    public ConsultantService(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    // ✅ Add a new consultant to the database
    public Consultant addConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    // ✅ Get a list of all consultants
    public List<Consultant> getAllConsultants() {
        return consultantRepository.findAll();
    }

    // ✅ Get a specific consultant by ID
    public Optional<Consultant> getConsultantById(Integer id) {
        return consultantRepository.findById(id);
    }

    // ✅ Delete a consultant by ID
    public void deleteConsultant(Integer id) {
        consultantRepository.deleteById(id);
    }

    // ✅ Update an existing consultant
    public Consultant updateConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }
}
