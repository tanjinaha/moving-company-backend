package com.tanjina.mvc.backend.controller;  // Location of this controller

import com.tanjina.mvc.backend.entity.ServiceType;
import com.tanjina.mvc.backend.service.ServiceTypeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
// @RestController tells Spring Boot this class handles HTTP requests and returns JSON responses
@RestController

// Base URL for all endpoints in this controller will start with /servicetypes
@RequestMapping("/servicetypes")
public class ServiceTypeController {

    // Reference to the ServiceTypeService to delegate business logic
    private final ServiceTypeService serviceTypeService;

    // Constructor Injection of the service
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    // POST /servicetypes
    // Adds a new service type
    @PostMapping
    public ResponseEntity<ServiceType> addServiceType(@RequestBody ServiceType serviceType) {
        ServiceType savedServiceType = serviceTypeService.addServiceType(serviceType);
        return ResponseEntity.status(201).body(savedServiceType);  // HTTP 201 Created
    }

    // GET /servicetypes
    // Retrieves all service types
    @GetMapping
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        List<ServiceType> allServiceTypes = serviceTypeService.getAllServiceTypes();
        return ResponseEntity.ok(allServiceTypes);  // HTTP 200 OK
    }

    // GET /servicetypes/{id}
    // Retrieves a service type by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceType> getServiceTypeById(@PathVariable Integer id) {
        Optional<ServiceType> serviceType = serviceTypeService.getServiceTypeById(id);
        return serviceType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());  // 404 if not found
    }

    // PUT /servicetypes
    // Updates an existing service type
    @PutMapping
    public ResponseEntity<ServiceType> updateServiceType(@RequestBody ServiceType serviceType) {
        ServiceType updatedServiceType = serviceTypeService.updateServiceType(serviceType);
        return ResponseEntity.ok(updatedServiceType);  // HTTP 200 OK
    }

    // DELETE /servicetypes/{id}
    // Deletes a service type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Integer id) {
        serviceTypeService.deleteServiceType(id);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content
    }
}