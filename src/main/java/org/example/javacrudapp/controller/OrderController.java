package org.example.javacrudapp.controller;

import org.example.javacrudapp.dto.OrderItemDto;
import org.example.javacrudapp.dto.OrderRequestDto;
import org.example.javacrudapp.exception.ResourceNotFoundException;
import org.example.javacrudapp.model.OrderHeader;
import org.example.javacrudapp.model.OrderItem;
import org.example.javacrudapp.repository.OrderRepository;
import org.example.javacrudapp.repository.OrderItemRepository;
import org.example.javacrudapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderItemRepository orderItemRepo;

    @PostMapping
    public ResponseEntity<OrderHeader> createOrder(@RequestBody OrderRequestDto request) {
        OrderHeader newOrder = orderService.createOrder(request);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<OrderHeader> getAllOrders() {
//        return orderRepo.findAll();
//    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderHeader> getOrder(@PathVariable Integer orderId) {
        OrderHeader order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderHeader> updateOrder(@PathVariable Integer orderId, @RequestBody OrderRequestDto request) {
         // Implement update logic in service if needed
         return ResponseEntity.ok(null); 
    }

    // Delete Order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        if (!orderRepo.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        orderRepo.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItem> addOrderItem(@PathVariable Integer orderId, @RequestBody OrderItemDto itemDto) {
        return ResponseEntity.ok(orderService.addOrderItem(orderId, itemDto));
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer orderId, @PathVariable Integer itemId) {
        if (!orderItemRepo.existsById(itemId)) {
             throw new ResourceNotFoundException("Item not found");
        }
        orderItemRepo.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }
}