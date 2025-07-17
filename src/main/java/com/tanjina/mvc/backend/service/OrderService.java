package com.tanjina.mvc.backend.service;

import com.tanjina.mvc.backend.dto.CreateOrderDTO;
import com.tanjina.mvc.backend.dto.OrderDetailsDTO;
import com.tanjina.mvc.backend.entity.*;
import com.tanjina.mvc.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    // ✅ Repositories used to interact with the database
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SalesConsultantRepository salesConsultantRepository;
    private final OrderServiceTypeRepository orderServiceTypeRepository;
    private final ServiceTypeRepository serviceTypeRepository;  // ✅ Newly added

    // ✅ Constructor injection (Spring will provide the repositories automatically)
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        SalesConsultantRepository salesConsultantRepository,
                        OrderServiceTypeRepository orderServiceTypeRepository,
                        ServiceTypeRepository serviceTypeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.salesConsultantRepository = salesConsultantRepository;
        this.orderServiceTypeRepository = orderServiceTypeRepository;
        this.serviceTypeRepository = serviceTypeRepository;  // ✅ Store the provided repository
    }

    // ✅ Save a basic order
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // ✅ Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Get one order by ID
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    // ✅ Update an order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // ✅ Delete order by ID
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // ✅ Get detailed list of orders with customer & consultant names
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

    // ✅ MAIN METHOD — Create a full order with all required info
    public Order createOrderFromDTO(CreateOrderDTO dto) {
        // 1️⃣ Create or reuse existing customer
        Customer customer = customerRepository
                .findByCustomerPhoneAndCustomerEmail(Long.parseLong(dto.getCustomerPhone()), dto.getCustomerEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setCustomerName(dto.getCustomerName());
                    newCustomer.setCustomerPhone(Long.parseLong(dto.getCustomerPhone()));
                    newCustomer.setCustomerEmail(dto.getCustomerEmail());
                    return customerRepository.save(newCustomer);
                });

        // 2️⃣ Create or reuse existing consultant
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

        // 4️⃣ Save service details in OrderServiceType
        OrderServiceType orderService = new OrderServiceType();
        orderService.setOrder(savedOrder);
        orderService.setFromAddress(dto.getFromAddress());
        orderService.setToAddress(dto.getToAddress());
        orderService.setScheduleDate(dto.getScheduleDate());
        orderService.setPrice(dto.getPrice());

        // ✅ Now fetch the ServiceType entity using the serviceId
        Integer serviceId = dto.getServiceId();
        ServiceType serviceType = serviceTypeRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Invalid service type ID: " + serviceId));
        orderService.setServiceType(serviceType);

        // ✅ Save the service entry
        orderServiceTypeRepository.save(orderService);

        return savedOrder;
    }
}
