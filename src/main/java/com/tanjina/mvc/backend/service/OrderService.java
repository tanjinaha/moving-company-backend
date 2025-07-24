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

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ConsultantRepository salesConsultantRepository;
    private final OrderServiceTypeRepository orderServiceTypeRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ConsultantRepository salesConsultantRepository,
                        OrderServiceTypeRepository orderServiceTypeRepository,
                        ServiceTypeRepository serviceTypeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.salesConsultantRepository = salesConsultantRepository;
        this.orderServiceTypeRepository = orderServiceTypeRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // ✅ Return order details with customer, consultant, and service name
    public List<OrderDetailsDTO> getOrderDetails() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            Optional<Customer> customerOpt = customerRepository.findById(order.getCustomerId());
            String customerName = customerOpt.map(Customer::getCustomerName).orElse("Unknown Customer");

            Optional<Consultant> consultantOpt = salesConsultantRepository.findById(order.getConsultantId());
            String consultantName = consultantOpt.map(Consultant::getConsultantName).orElse("Unknown Consultant");

            OrderServiceType orderServiceType = order.getOrderServiceTypes()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (orderServiceType == null) {
                return new OrderDetailsDTO(
                        order.getOrderId(),
                        customerName,
                        consultantName,
                        order.getNote(),
                        "No service",
                        "-", "-", null, null
                );
            }

            return new OrderDetailsDTO(
                    order.getOrderId(),
                    customerName,
                    consultantName,
                    order.getNote(),
                    orderServiceType.getServiceType() != null ? orderServiceType.getServiceType().getServiceName() : "Unknown Service",
                    orderServiceType.getFromAddress(),
                    orderServiceType.getToAddress(),
                    orderServiceType.getScheduleDate(),
                    orderServiceType.getPrice()
            );
        }).collect(Collectors.toList());
    }

    // ✅ Handles new order creation with customer + consultant reuse
    public Order createOrderFromDTO(CreateOrderDTO dto) {
        // 🚨 Required field checks
        if (dto.getServiceId() == null) throw new RuntimeException("❌ Service type is required.");
        if (dto.getFromAddress() == null || dto.getFromAddress().isEmpty()) throw new RuntimeException("❌ From address is required.");
        if (dto.getToAddress() == null || dto.getToAddress().isEmpty()) throw new RuntimeException("❌ To address is required.");
        if (dto.getScheduleDate() == null) throw new RuntimeException("❌ Schedule date is required.");
        if (dto.getPrice() == null || dto.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0)
            throw new RuntimeException("❌ Price must be greater than 0.");
        if (dto.getNote() == null || dto.getNote().isEmpty()) throw new RuntimeException("❌ Note is required.");

        // ✅ 1. Reuse or create customer
        Optional<Customer> existingCustomer = customerRepository.findMatchingCustomer(
                dto.getCustomerName(),
                dto.getCustomerEmail(),
                Long.parseLong(dto.getCustomerPhone())  // ✅ Convert string to number
        );



        Customer customer = existingCustomer.orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerName(dto.getCustomerName());
            newCustomer.setCustomerPhone(Long.parseLong(dto.getCustomerPhone()));
            newCustomer.setCustomerEmail(dto.getCustomerEmail());
            return customerRepository.save(newCustomer);
        });

        // ✅ 2. Reuse or create consultant
        Optional<Consultant> existingConsultant = salesConsultantRepository
                .findByConsultantPhoneAndConsultantEmail(Long.parseLong(dto.getConsultantPhone()), dto.getConsultantEmail());

        Consultant consultant = existingConsultant.orElseGet(() -> {
            Consultant newConsultant = new Consultant();
            newConsultant.setConsultantName(dto.getConsultantName());
            newConsultant.setConsultantPhone(Long.parseLong(dto.getConsultantPhone()));
            newConsultant.setConsultantEmail(dto.getConsultantEmail());
            return salesConsultantRepository.save(newConsultant);
        });

        // ✅ 3. Create new order
        Order order = new Order();
        order.setCustomerId(customer.getCustomerId());
        order.setConsultantId(consultant.getConsultantId());
        order.setNote(dto.getNote());
        Order savedOrder = orderRepository.save(order);

        // ✅ 4. Create and save service
        OrderServiceType orderService = new OrderServiceType();
        orderService.setOrder(savedOrder);
        orderService.setFromAddress(dto.getFromAddress());
        orderService.setToAddress(dto.getToAddress());
        orderService.setScheduleDate(dto.getScheduleDate());
        orderService.setPrice(dto.getPrice());

        ServiceType serviceType = serviceTypeRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Invalid service type ID: " + dto.getServiceId()));
        orderService.setServiceType(serviceType);

        orderServiceTypeRepository.save(orderService);

        return savedOrder;
    }
    // ✅ This updates an existing order's service and other details
    public void updateOrderDetailsFromDTO(Integer orderId, OrderDetailsDTO dto) {

        // 🔍 Step 1: Find the order by ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // ✏️ Save note if changed
        if (dto.getNote() != null) {
            order.setNote(dto.getNote());
            orderRepository.save(order);
        }

        // 🔍 Step 2: Find the related service row
        OrderServiceType orderService = orderServiceTypeRepository.findByOrder(order)
                .orElseThrow(() -> new RuntimeException("OrderServiceType not found for orderId: " + orderId));

        // 🧭 Save service type if changed
        if (dto.getServiceId() != null) {
            ServiceType serviceType = serviceTypeRepository.findById(dto.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service type not found: " + dto.getServiceId()));
            orderService.setServiceType(serviceType);
            orderServiceTypeRepository.save(orderService);
        }

        // 🏠 Save from address if changed
        if (dto.getFromAddress() != null) {
            orderService.setFromAddress(dto.getFromAddress());
            orderServiceTypeRepository.save(orderService);
        }

        // 🏡 Save to address if changed
        if (dto.getToAddress() != null) {
            orderService.setToAddress(dto.getToAddress());
            orderServiceTypeRepository.save(orderService);
        }

        // 📅 Save schedule date if changed
        if (dto.getScheduleDate() != null) {
            orderService.setScheduleDate(dto.getScheduleDate());
            orderServiceTypeRepository.save(orderService);
        }

        // 💰 Save price if changed
        if (dto.getPrice() != null) {
            orderService.setPrice(dto.getPrice());
            orderServiceTypeRepository.save(orderService);
        }
    }


    // 🔍 This method finds all orders that match a customer's name (case-insensitive)
    public List<OrderDetailsDTO> findOrdersByCustomerName(String customerName) {
        // 1. Get all order details
        List<OrderDetailsDTO> allOrders = getOrderDetails();

        // 2. Filter by name (case-insensitive match)
        return allOrders.stream()
                .filter(order -> order.getCustomerName() != null &&
                        order.getCustomerName().toLowerCase().contains(customerName.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

}