package com.tanjina.mvc.backend.repository;  // This shows the folder/package where this file lives

// This import brings in the SalesConsultant class, so we can use it here
import com.tanjina.mvc.backend.entity.SalesConsultant;

// These imports bring in Spring Boot tools to talk to the database
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This tells Spring Boot that this file is a "Repository"
// A Repository is a tool that helps us work with the database
@Repository
public interface SalesConsultantRepository extends JpaRepository<SalesConsultant, Integer> {

    // What does this do?
    // This interface says:
    // "I want to use Spring Boot’s built-in tools to work with SalesConsultant objects"

    // We don't need to write any code here!
    // We automatically get useful methods like:
    // - save()         → to save a sales consultant to the database
    // - findById()     → to find a sales consultant by ID
    // - findAll()      → to get a list of all sales consultants
    // - deleteById()   → to delete a sales consultant by ID
}