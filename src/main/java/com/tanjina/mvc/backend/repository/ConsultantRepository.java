package com.tanjina.mvc.backend.repository;  // This shows the folder/package where this file lives

// Import the correct entity class
import com.tanjina.mvc.backend.entity.Consultant;

// Spring Data JPA and Spring Boot annotations
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// ✅ This tells Spring Boot: "This interface will help us talk to the database for SalesConsultant"
@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

    // ✅ Search for consultants by partial name (case-insensitive)
    List<Consultant> findByConsultantNameContainingIgnoreCase(String name);

    // ✅ Find an existing consultant by both phone and email (must match exactly)
    Optional<Consultant> findByConsultantPhoneAndConsultantEmail(Long consultantPhone, String consultantEmail);

    // ✅ We get save(), findAll(), findById(), deleteById() automatically from JpaRepository
}
