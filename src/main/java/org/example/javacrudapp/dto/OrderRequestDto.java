package org.example.javacrudapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequestDto {
    private LocalDate orderDate;
    private Integer customerId;
    private Integer shippingContactMechId;
    private Integer billingContactMechId;
    private List<OrderItemDto> orderItems;
}