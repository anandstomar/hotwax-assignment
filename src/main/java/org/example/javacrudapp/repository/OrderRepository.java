package org.example.javacrudapp.repository;

import org.example.javacrudapp.model.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderHeader, Integer> {}
