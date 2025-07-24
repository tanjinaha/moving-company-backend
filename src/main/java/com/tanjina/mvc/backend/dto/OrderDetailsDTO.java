package com.tanjina.mvc.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This is a simple Java class to hold combined order-related data
 * from multiple backend entities for transferring between backend and frontend.
 */
public class OrderDetailsDTO {

    // 🧾 Order basic info
    private Integer orderId;
    private String customerName;
    private String consultantName;
    private String note;

    // 🆕 OrderServiceType details
    private String serviceType;
    private String fromAddress;
    private String toAddress;
    private LocalDate scheduleDate;
    private BigDecimal price;

    private Integer serviceId;

    // ✅ Default constructor (needed by Spring for deserialization)
    public OrderDetailsDTO() {}

    // ✅ All-args constructor (used in OrderService to build the object)
    public OrderDetailsDTO(Integer orderId, String customerName, String consultantName, String note,
                           String serviceType, String fromAddress, String toAddress,
                           LocalDate scheduleDate, BigDecimal price) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.consultantName = consultantName;
        this.note = note;
        this.serviceType = serviceType;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.scheduleDate = scheduleDate;
        this.price = price;
    }

    // ✅ Getters and Setters (needed for frontend JSON mapping)

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
