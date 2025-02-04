package com.ecommerce.order.wrapper;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BillWrapper {
	private String billNumber;
    private Double totalAmount;
    private LocalDateTime timestamp;
    private String customerName;
    private String paymentMethod;
    private Double discountAmount;
}
