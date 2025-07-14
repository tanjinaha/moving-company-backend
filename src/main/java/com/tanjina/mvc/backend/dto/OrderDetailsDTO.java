package com.tanjina.mvc.backend.dto;

/**
 * This is a simple Java class to hold combined order-related data
 * from multiple backend entities for transferring between backend and frontend.
 */
public class OrderDetailsDTO {

    /**
     * Unique ID of the order
     * Comes from: Order entity → orderId (database column: orders.order_id)
     */
    private Integer orderId;

    /**
     * Name of the customer who placed the order
     * Comes from: Customer entity → customerName (database column: customers.customer_name)
     */
    private String customerName;

    /**
     * Name of the sales consultant handling the order
     * Comes from: SalesConsultant entity → consultantName (database column: salesconsultants.consultant_name)
     */
    private String consultantName;

    /**
     * Additional notes about the order, like special instructions
     * Comes from: Order entity → note (database column: orders.note)
     */
    private String note;

    /**
     * Default constructor — needed by some frameworks (like JSON converters) to create an empty object first
     */
    public OrderDetailsDTO() {}

    /**
     * Constructor to quickly create an OrderDetailsDTO with all combined information
     *
     * @param orderId       Unique order ID from Order entity
     * @param customerName  Customer's name from Customer entity
     * @param consultantName Consultant's name from SalesConsultant entity
     * @param note          Order note from Order entity
     */
    public OrderDetailsDTO(Integer orderId, String customerName, String consultantName, String note) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.consultantName = consultantName;
        this.note = note;
    }

    // Getters and setters follow — used to access and modify data during serialization/deserialization

    /**
     * Getter for orderId (used to read the order ID)
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Setter for orderId (used by frameworks during deserialization)
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Getter for customerName (used to read the customer name)
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for customerName (used to set the customer name)
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for consultantName (used to read the consultant name)
     */
    public String getConsultantName() {
        return consultantName;
    }

    /**
     * Setter for consultantName (used to set the consultant name)
     */
    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    /**
     * Getter for note (used to read the note)
     */
    public String getNote() {
        return note;
    }

    /**
     * Setter for note (used to set the note)
     */
    public void setNote(String note) {
        this.note = note;
    }
}