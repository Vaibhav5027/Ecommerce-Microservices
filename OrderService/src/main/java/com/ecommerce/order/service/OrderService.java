package com.ecommerce.order.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.order.dto.OrderDTO;



public interface OrderService {
 
	ResponseEntity<String> buyOrder(OrderDTO order);
	void kafka(String string);

}
