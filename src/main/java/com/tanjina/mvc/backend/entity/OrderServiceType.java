package com.tanjina.mvc.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing a row in the 'orderservicetypes' table.
 * This table links Orders to ServiceTypes and includes extra details like addresses, schedule, and price.
 */
@Entity
@Table(name = "orderservicetypes")
public class OrderServiceType {

    /**
     * Primary key column 'orderservicetype_id', auto-incremented.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderservicetype_id")
    private Integer orderServiceTypeId;

    /**
     * Foreign key column 'order_id' linking to the Orders table.
     */
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    /**
     * Foreign key column 'service_id' linking to the ServiceTypes table.
     */
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    /**
     * Column 'from_address' storing the starting address for this service.
     */
    @Column(name = "from_address", length = 255)
    private String fromAddress;

    /**
     * Column 'to_address' storing the destination address for this service.
     */
    @Column(name = "to_address", length = 255)
    private String toAddress;

    /**
     * Column 'schedule_date' storing the scheduled date for this service.
     */
    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    /**
     * Column 'price' storing the price for this service.
     * BigDecimal is used for precise monetary values.
     */
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Default no-argument constructor required by JPA.
     */
    public OrderServiceType() {}

    /**
     * Parameterized constructor for easy instantiation.
     */
    public OrderServiceType(Integer orderServiceTypeId, Integer orderId, Integer serviceId,
                            String fromAddress, String toAddress, LocalDate scheduleDate, BigDecimal price) {
        this.orderServiceTypeId = orderServiceTypeId;
        this.orderId = orderId;
        this.serviceId = serviceId;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.scheduleDate = scheduleDate;
        this.price = price;
    }

    // Getters and setters

    public Integer getOrderServiceTypeId() {
        return orderServiceTypeId;
    }

    public void setOrderServiceTypeId(Integer orderServiceTypeId) {
        this.orderServiceTypeId = orderServiceTypeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}