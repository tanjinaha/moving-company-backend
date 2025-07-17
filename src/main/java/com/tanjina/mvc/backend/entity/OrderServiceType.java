package com.tanjina.mvc.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orderservicetypes")
public class OrderServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderservicetype_id")
    private Integer orderServiceTypeId;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceType serviceType;

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

    public OrderServiceType() {}

    public OrderServiceType(Integer orderServiceTypeId, ServiceType serviceType, Order order,
                            String fromAddress, String toAddress, LocalDate scheduleDate, BigDecimal price) {
        this.orderServiceTypeId = orderServiceTypeId;
        this.serviceType = serviceType;
        this.order = order;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.scheduleDate = scheduleDate;
        this.price = price;
    }

    public Integer getOrderServiceTypeId() {
        return orderServiceTypeId;
    }

    public void setOrderServiceTypeId(Integer orderServiceTypeId) {
        this.orderServiceTypeId = orderServiceTypeId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
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
