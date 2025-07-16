package com.tanjina.mvc.backend.entity;

import com.tanjina.mvc.backend.entity.ServiceTypeEnum; // ✅ import the enum
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This entity represents one service included in an order.
 * It connects to the Order table and stores the type of service (as enum), address, date, and price.
 */
@Entity
@Table(name = "orderservicetypes")
public class OrderServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderservicetype_id")
    private Integer orderServiceTypeId;

    // ✅ Use enum to represent the service type (MOVING, CLEANING, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceTypeEnum serviceType;

    // ✅ Link to the Order entity
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "from_address", length = 255)
    private String fromAddress;

    @Column(name = "to_address", length = 255)
    private String toAddress;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    // ✅ Default constructor
    public OrderServiceType() {}

    // ✅ Constructor with all fields (uses ServiceTypeEnum)
    public OrderServiceType(Integer orderServiceTypeId, ServiceTypeEnum serviceType, Order order,
                            String fromAddress, String toAddress, LocalDate scheduleDate, BigDecimal price) {
        this.orderServiceTypeId = orderServiceTypeId;
        this.serviceType = serviceType;
        this.order = order;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.scheduleDate = scheduleDate;
        this.price = price;
    }

    // ✅ Getters and Setters
    public Integer getOrderServiceTypeId() {
        return orderServiceTypeId;
    }

    public void setOrderServiceTypeId(Integer orderServiceTypeId) {
        this.orderServiceTypeId = orderServiceTypeId;
    }

    public ServiceTypeEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeEnum serviceType) {
        this.serviceType = serviceType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
