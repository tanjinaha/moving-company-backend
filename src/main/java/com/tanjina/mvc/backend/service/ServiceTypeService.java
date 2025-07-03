package com.tanjina.mvc.backend.service;  // Package location for this service class

// Importing the ServiceType entity and repository so we can use them here
import com.tanjina.mvc.backend.entity.ServiceType;
import com.tanjina.mvc.backend.repository.ServiceTypeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that contains business logic related to ServiceType.
 * This class communicates with the repository to perform CRUD operations.
 */
@Service  // Marks this class as a Spring service component
public class ServiceTypeService {

    // Repository that handles database operations for ServiceType
    private final ServiceTypeRepository serviceTypeRepository;

    /**
     * Constructor Injection:
     * Spring automatically provides an instance of ServiceTypeRepository when creating this service.
     * This promotes loose coupling and easier testing.
     */
    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    /**
     * Adds a new ServiceType to the database.
     *
     * @param serviceType The ServiceType object to add
     * @return The saved ServiceType with generated ID
     */
    public ServiceType addServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    /**
     * Retrieves all ServiceType entries from the database.
     *
     * @return A list of all ServiceTypes
     */
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    /**
     * Retrieves a ServiceType by its ID.
     *
     * @param id The ID of the ServiceType to retrieve
     * @return An Optional containing the ServiceType if found, or empty if not found
     */
    public Optional<ServiceType> getServiceTypeById(Integer id) {
        return serviceTypeRepository.findById(id);
    }

    /**
     * Updates an existing ServiceType in the database.
     * If the ID exists, the entity is updated; otherwise, a new one is created.
     *
     * @param serviceType The ServiceType object with updated data
     * @return The updated (or newly created) ServiceType
     */
    public ServiceType updateServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    /**
     * Deletes a ServiceType by its ID.
     *
     * @param id The ID of the ServiceType to delete
     */
    public void deleteServiceType(Integer id) {
        serviceTypeRepository.deleteById(id);
    }

    // Example of a custom query method usage:
    // public List<ServiceType> getServiceTypesByName(ServiceTypeEnum serviceName) {
    //     return serviceTypeRepository.findByServiceName(serviceName);
    // }
}