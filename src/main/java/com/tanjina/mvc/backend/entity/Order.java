package com.tanjina.mvc.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "consultant_id", nullable = false)
    private Integer consultantId;

    @Column(name = "note", length = 255)
    private String note;

    // ✅ Add One-to-Many relationship to OrderServiceType
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderServiceType> orderServiceTypes;

    public Order() {}

    public Order(Integer orderId, Integer customerId, Integer consultantId, String note) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.consultantId = consultantId;
        this.note = note;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Integer consultantId) {
        this.consultantId = consultantId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // ✅ Getter and Setter for orderServiceTypes
    public List<OrderServiceType> getOrderServiceTypes() {
        return orderServiceTypes;
    }

    public void setOrderServiceTypes(List<OrderServiceType> orderServiceTypes) {
        this.orderServiceTypes = orderServiceTypes;
    }
}
