package com.tanjina.mvc.backend.entity;  // This tells Java where this class is located in the project

import jakarta.persistence.*;  // These are JPA annotations used to connect Java classes to database tables

// This annotation tells Spring Boot: "This class is a database table"
@Entity

// This specifies the name of the table in MySQL: "customers"
@Table(name = "customers")
public class Customer {

    // This field maps to the "customer_id" column in the table
    // It will be the PRIMARY KEY and AUTO-INCREMENTED by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    // This field maps to "customer_name" (type: VARCHAR(100), NOT NULL)
    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    // This field maps to "customer_phone" (type: BIGINT, nullable)
    @Column(name = "customer_phone")
    private Long customerPhone;

    // This field maps to "customer_email" (type: VARCHAR(100), nullable)
    @Column(name = "customer_email", length = 100)
    private String customerEmail;

    // ------------------------------
    // 👇 Constructors
    // ------------------------------

    // This is the default constructor (required by Spring Boot and JPA)
    public Customer() {
    }

    // This constructor is used to create a new customer object with name, phone, and email
    // The ID is not passed here because it is generated automatically
    public Customer(String customerName, Long customerPhone, String customerEmail) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    // ------------------------------
    // 👇 Getters and Setters
    // These allow us to access and modify the fields above
    // ------------------------------

    // Get the customer's ID
    public Integer getCustomerId() {
        return customerId;
    }

    // Set (change) the customer's ID
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    // Get the customer's name
    public String getCustomerName() {
        return customerName;
    }

    // Set (change) the customer's name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Get the customer's phone number
    public Long getCustomerPhone() {
        return customerPhone;
    }

    // Set (change) the customer's phone number
    public void setCustomerPhone(Long customerPhone) {
        this.customerPhone = customerPhone;
    }

    // Get the customer's email address
    public String getCustomerEmail() {
        return customerEmail;
    }

    // Set (change) the customer's email address
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}