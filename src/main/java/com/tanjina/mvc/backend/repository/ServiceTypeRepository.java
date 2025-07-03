package com.tanjina.mvc.backend.repository;  // Package location of this interface

// Import the ServiceType entity so we can use it here
import com.tanjina.mvc.backend.entity.ServiceType;

// Spring Data JPA imports to build repository interfaces easily
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Marks this interface as a Spring-managed "Repository" component
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {

    // What does this interface do?
    // It allows us to perform CRUD (Create, Read, Update, Delete) operations on ServiceType entities
    // without writing any SQL or implementation code.
    //
    // By extending JpaRepository, we automatically get:
    // - save()         → to insert or update a ServiceType
    // - findById()     → to find a ServiceType by its primary key (serviceId)
    // - findAll()      → to get a list of all ServiceTypes
    // - deleteById()   → to delete a ServiceType by its primary key
    //
    // We can also add custom query methods by simply defining method signatures here,
    // and Spring Data JPA will implement them automatically.
    //
    // Example: List<ServiceType> findByServiceName(ServiceTypeEnum serviceName);
}
