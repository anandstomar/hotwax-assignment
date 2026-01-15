package org.example.javacrudapp.repository;

import org.example.javacrudapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {} 
