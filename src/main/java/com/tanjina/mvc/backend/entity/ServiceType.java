package com.tanjina.mvc.backend.entity;

import jakarta.persistence.*;

/**
 * Entity class representing a service type in the database.
 * Maps to the 'servicetypes' table.
 */
@Entity  // Marks this class as a JPA entity to be managed by Hibernate
@Table(name = "servicetypes")  // Maps this entity to the 'servicetypes' table in the database
public class ServiceType {

    // This field maps to the "service_id" column in the table
    // It will be the PRIMARY KEY and AUTO-INCREMENTED by the database
    @Id  // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment strategy for MySQL
    @Column(name = "service_id")  // Explicitly map to the 'service_id' column
    private Integer serviceId;

    // This field maps to the "service_name" column in the table
    // It stores an enum value representing the type of service (e.g., MOVING, CLEANING)
    @Enumerated(EnumType.STRING)  // Store the enum value as a string in the database
    @Column(name = "service_name", nullable = false)  // Not nullable because service_name is required
    private ServiceTypeEnum serviceName;

    /**
     * Default no-argument constructor required by JPA.
     */
    public ServiceType() {}

    /**
     * Constructor with parameters to easily create new ServiceType objects.
     *
     * @param serviceId The unique ID of the service type (usually auto-generated)
     * @param serviceName The enum value representing the service type
     */
    public ServiceType(Integer serviceId, ServiceTypeEnum serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    // Getter for serviceId
    public Integer getServiceId() {
        return serviceId;
    }

    // Setter for serviceId
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    // Getter for serviceName
    public ServiceTypeEnum getServiceName() {
        return serviceName;
    }

    // Setter for serviceName
    public void setServiceName(ServiceTypeEnum serviceName) {
        this.serviceName = serviceName;
    }
}