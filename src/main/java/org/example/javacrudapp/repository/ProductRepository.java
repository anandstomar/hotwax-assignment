package org.example.javacrudapp.repository;

import org.example.javacrudapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {} 
