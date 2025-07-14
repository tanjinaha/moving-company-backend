package com.tanjina.mvc.backend.service;

import com.tanjina.mvc.backend.dto.OrderDetailsDTO; // New import: DTO class to transfer combined order info
import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.entity.Customer;
import com.tanjina.mvc.backend.entity.SalesConsultant;
import com.tanjina.mvc.backend.repository.OrderRepository;
import com.tanjina.mvc.backend.repository.CustomerRepository;
import com.tanjina.mvc.backend.repository.SalesConsultantRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service  // Marks this class as a Spring service (business logic layer)
public class OrderService {

    // Repositories to access data from database tables
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SalesConsultantRepository salesConsultantRepository;

    // Constructor injection to provide repositories — promotes loose coupling & easier testing
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        SalesConsultantRepository salesConsultantRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.salesConsultantRepository = salesConsultantRepository;
    }

    // ---- Basic CRUD operations for Order entity ----

    // Add a new order and save to DB
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // Retrieve all orders from DB
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve an order by ID, wrapped in Optional in case it doesn't exist
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    // Update an order, or create if it doesn't exist yet
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete an order by its ID
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // ---- New: Method using OrderDetailsDTO ----

    /**
     * This method fetches all orders and "joins" related information from Customer and SalesConsultant.
     *
     * Why use DTO here?
     * - Because the frontend UI usually needs combined info (order + customer name + consultant name)
     * - Instead of sending raw Order objects with only IDs, we send a friendly combined view.
     *
     * How does it work?
     * - We fetch all orders from the order repository.
     * - For each order, we look up the related Customer and SalesConsultant by their IDs.
     * - If customer or consultant is missing, we provide a fallback string ("Unknown Customer" / "Unknown Consultant").
     * - We create a new OrderDetailsDTO instance containing:
     *      • orderId from Order
     *      • customerName from Customer
     *      • consultantName from SalesConsultant
     *      • note from Order
     * - Finally, we collect all DTOs into a list and return it.
     */
    public List<OrderDetailsDTO> getOrderDetails() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            // Look up customer by ID
            Optional<Customer> customerOpt = customerRepository.findById(order.getCustomerId());
            // If found, get customerName, else fallback to "Unknown Customer"
            String customerName = customerOpt.map(Customer::getCustomerName).orElse("Unknown Customer");

            // Look up consultant by ID
            Optional<SalesConsultant> consultantOpt = salesConsultantRepository.findById(order.getConsultantId());
            // If found, get consultantName, else fallback to "Unknown Consultant"
            String consultantName = consultantOpt.map(SalesConsultant::getConsultantName).orElse("Unknown Consultant");

            // Create and return DTO combining all needed data
            return new OrderDetailsDTO(order.getOrderId(), customerName, consultantName, order.getNote());
        }).collect(Collectors.toList());
    }
}