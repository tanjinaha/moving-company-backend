package com.tanjina.mvc.backend.entity;

// Import JPA annotations to map this class to database table
import jakarta.persistence.*;

/**
 * Entity class representing an Order record in the database.
 * This class maps to the 'orders' table.
 */
@Entity  // Marks this class as a JPA entity that corresponds to a database table
@Table(name = "orders")  // Specifies the exact database table name this entity maps to
public class Order {

    /**
     * This field maps to the 'order_id' column in the orders table.
     * It is the primary key, uniquely identifying each order.
     *
     * @Id marks this as the primary key field.
     * @GeneratedValue with IDENTITY strategy means MySQL will auto-increment this value.
     * @Column specifies the exact database column name.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * This field maps to the 'customer_id' column, representing which customer placed this order.
     *
     * @Column specifies the database column name and sets this field as NOT NULL.
     */
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    /**
     * This field maps to the 'consultant_id' column, representing the sales consultant who handled this order.
     *
     * @Column specifies the database column name and sets this field as NOT NULL.
     */
    @Column(name = "consultant_id", nullable = false)
    private Integer consultantId;

    /**
     * This field maps to the 'note' column, an optional text field for extra information about the order.
     *
     * @Column specifies the database column name and maximum length of 255 characters.
     * No 'nullable = false' means it can be NULL.
     */
    @Column(name = "note", length = 255)
    private String note;

    /**
     * Default no-argument constructor required by JPA.
     * JPA uses this constructor to create instances through reflection.
     */
    public Order() {}

    /**
     * Parameterized constructor to easily create Order instances with all data.
     *
     * @param orderId      Unique identifier of the order.
     * @param customerId   ID of the customer who placed the order.
     * @param consultantId ID of the sales consultant handling the order.
     * @param note         Additional note about the order.
     */
    public Order(Integer orderId, Integer customerId, Integer consultantId, String note) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.consultantId = consultantId;
        this.note = note;
    }

    /**
     * Gets the unique order ID.
     *
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Sets the unique order ID.
     * Usually managed by the database and not set manually.
     *
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the customer ID who placed this order.
     *
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for this order.
     *
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the sales consultant ID who handled this order.
     *
     * @return the consultantId
     */
    public Integer getConsultantId() {
        return consultantId;
    }

    /**
     * Sets the sales consultant ID for this order.
     *
     * @param consultantId the consultantId to set
     */
    public void setConsultantId(Integer consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * Gets the note about this order.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note about this order.
     *
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
}