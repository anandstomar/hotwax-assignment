package org.example.javacrudapp.service;

import org.example.javacrudapp.dto.OrderItemDto;
import org.example.javacrudapp.dto.OrderRequestDto;
import org.example.javacrudapp.exception.ResourceNotFoundException;
import org.example.javacrudapp.model.*;
import org.example.javacrudapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private ContactMechRepository contactRepo;
    @Autowired private OrderItemRepository orderItemRepo;

    @Transactional
    public OrderHeader createOrder(OrderRequestDto request) {
        OrderHeader order = new OrderHeader();
        order.setOrderDate(request.getOrderDate());

        // Fetch and Validate Entities
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        ContactMech shipping = contactRepo.findById(request.getShippingContactMechId())
                .orElseThrow(() -> new ResourceNotFoundException("Shipping Contact not found"));
        ContactMech billing = contactRepo.findById(request.getBillingContactMechId())
                .orElseThrow(() -> new ResourceNotFoundException("Billing Contact not found"));

        order.setCustomer(customer);
        order.setShippingContact(shipping);
        order.setBillingContact(billing);

        // Process Items
        for (OrderItemDto itemDto : request.getOrderItems()) {
            Product product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemDto.getProductId()));
            
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setStatus(itemDto.getStatus());
            
            // Link item to order
            order.addOrderItem(item);
        }

        return orderRepo.save(order);
    }

    public OrderItem addOrderItem(Integer orderId, OrderItemDto itemDto) {
        OrderHeader order = orderRepo.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
            
        Product product = productRepo.findById(itemDto.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(itemDto.getQuantity());
        item.setStatus(itemDto.getStatus());
        item.setOrderHeader(order);
        
        return orderItemRepo.save(item);
    }
}
