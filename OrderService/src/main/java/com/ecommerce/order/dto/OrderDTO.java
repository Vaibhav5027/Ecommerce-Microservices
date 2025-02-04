package com.ecommerce.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.order.model.OrderItems;

import lombok.Data;


@Data
public class OrderDTO {
	private String email;
	private double totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItems> items; 
	
}
