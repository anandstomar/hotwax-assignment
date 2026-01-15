package org.example.javacrudapp.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Integer productId;
    private Integer quantity;
    private String status;
} 
