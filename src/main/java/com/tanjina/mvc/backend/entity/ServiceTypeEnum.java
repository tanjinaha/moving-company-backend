package com.tanjina.mvc.backend.entity;

/**
 * Enum representing all allowed service types.
 * This matches the ENUM values defined in the MySQL 'servicetypes' table.
 */
public enum ServiceTypeEnum {
    MOVING,         // Represents a moving service
    CLEANING,       // Represents a cleaning service
    PACKING,        // Represents a packing service
    CLEANING_DELUXE // Represents a deluxe cleaning service
}