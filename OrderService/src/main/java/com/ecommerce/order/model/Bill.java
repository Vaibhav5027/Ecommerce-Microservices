package com.ecommerce.order.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billId;
	private String billNumber;
	private List<Long> productIds; // List of product IDs
	private List<Integer> quantities;
    private Double totalAmount;
    private LocalDateTime timestamp;
    private String customerName;
    private String paymentMethod;
//    private Double discountAmount;
//    private Double taxAmount;

}
