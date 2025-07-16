package com.tanjina.mvc.backend.service;

import com.tanjina.mvc.backend.dto.CreateOrderDTO;
import com.tanjina.mvc.backend.dto.OrderDetailsDTO;
import com.tanjina.mvc.backend.entity.Customer;
import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.entity.OrderServiceType;
import com.tanjina.mvc.backend.entity.SalesConsultant;
import com.tanjina.mvc.backend.entity.ServiceTypeEnum;
import com.tanjina.mvc.backend.repository.CustomerRepository;
import com.tanjina.mvc.backend.repository.OrderRepository;
import com.tanjina.mvc.backend.repository.OrderServiceTypeRepository;
import com.tanjina.mvc.backend.repository.SalesConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SalesConsultantRepository salesConsultantRepository;
    private final OrderServiceTypeRepository orderServiceTypeRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        SalesConsultantRepository salesConsultantRepository,
                        OrderServiceTypeRepository orderServiceTypeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.salesConsultantRepository = salesConsultantRepository;
        this.orderServiceTypeRepository = orderServiceTypeRepository;
    }

    // Save a basic order
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get one order by ID
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    // Update an order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete order by ID
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // Get detailed list of orders with customer & consultant names
    public List<OrderDetailsDTO> getOrderDetails() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            Optional<Customer> customerOpt = customerRepository.findById(order.getCustomerId());
            String customerName = customerOpt.map(Customer::getCustomerName).orElse("Unknown Customer");

            Optional<SalesConsultant> consultantOpt = salesConsultantRepository.findById(order.getConsultantId());
            String consultantName = consultantOpt.map(SalesConsultant::getConsultantName).orElse("Unknown Consultant");

            return new OrderDetailsDTO(order.getOrderId(), customerName, consultantName, order.getNote());
        }).collect(Collectors.toList());
    }

    // ✅ MAIN method to create a full order with customer, consultant, and service info
    public Order createOrderFromDTO(CreateOrderDTO dto) {
        // 1️⃣ Find or create customer
        Customer customer = customerRepository
                .findByCustomerPhoneAndCustomerEmail(Long.parseLong(dto.getCustomerPhone()), dto.getCustomerEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setCustomerName(dto.getCustomerName());
                    newCustomer.setCustomerPhone(Long.parseLong(dto.getCustomerPhone()));
                    newCustomer.setCustomerEmail(dto.getCustomerEmail());
                    return customerRepository.save(newCustomer);
                });

        // 2️⃣ Find or create consultant
        SalesConsultant consultant = salesConsultantRepository
                .findByConsultantPhoneAndConsultantEmail(Long.parseLong(dto.getConsultantPhone()), dto.getConsultantEmail())
                .orElseGet(() -> {
                    SalesConsultant newConsultant = new SalesConsultant();
                    newConsultant.setConsultantName(dto.getConsultantName());
                    newConsultant.setConsultantPhone(Long.parseLong(dto.getConsultantPhone()));
                    newConsultant.setConsultantEmail(dto.getConsultantEmail());
                    return salesConsultantRepository.save(newConsultant);
                });

        // 3️⃣ Save the Order
        Order order = new Order();
        order.setCustomerId(customer.getCustomerId());
        order.setConsultantId(consultant.getConsultantId());
        order.setNote(dto.getNote());
        Order savedOrder = orderRepository.save(order);

        // 4️⃣ Save OrderServiceType using ENUM (✔ FIXED)
        OrderServiceType orderService = new OrderServiceType();
        orderService.setOrder(savedOrder);
        orderService.setServiceType(ServiceTypeEnum.valueOf(dto.getServiceType())); // ✅ FIXED enum usage
        orderService.setFromAddress(dto.getFromAddress());
        orderService.setToAddress(dto.getToAddress());
        orderService.setScheduleDate(dto.getScheduleDate());
        orderService.setPrice(dto.getPrice());
        orderServiceTypeRepository.save(orderService);

        return savedOrder;
    }
}
