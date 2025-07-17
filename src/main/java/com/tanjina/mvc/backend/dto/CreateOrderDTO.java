package com.tanjina.mvc.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class collects all data needed to create a full order.
 * It is used when frontend sends the form data to the backend.
 */
public class CreateOrderDTO {

    // ✅ Customer information (to create or find the customer)
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    // ✅ Consultant information (to create or find the sales consultant)
    private String consultantName;
    private String consultantPhone;
    private String consultantEmail;

    // ✅ This holds the selected service ID (MOVING, CLEANING, etc.)
    // It matches service_id in the servicetypes table
    private Integer serviceId;

    // ✅ Addresses for the service
    private String fromAddress;
    private String toAddress;

    // ✅ Scheduled date for when the service will happen
    private LocalDate scheduleDate;

    // ✅ Total price of the service
    private BigDecimal price;

    // ✅ Optional note about the order (ex: "Urgent", "Needs packing")
    private String note;

    // -------- Getters and Setters (to read/write values) --------

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
