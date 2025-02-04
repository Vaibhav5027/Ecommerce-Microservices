package com.common.events.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
	private Integer orderId;
	private String email;
	private double amount;
	private List<String> orderedProducts;
	private LocalDateTime orderDate;
}
