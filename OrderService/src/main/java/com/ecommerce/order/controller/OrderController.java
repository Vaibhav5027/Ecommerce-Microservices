package com.ecommerce.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItems;
import com.ecommerce.order.service.BillService;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.order.wrapper.BillWrapper;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	@Autowired
	private BillService service;
	
	@Autowired
	private OrderService orderService;
    
   @PostMapping("/buyNow")
   ResponseEntity<String> buyBow(@RequestBody OrderDTO order){
	  return orderService.buyOrder(order);
//	   return null;
   }
   
   @PostMapping("/checkOut")
   ResponseEntity<String> checkOut(@RequestBody Order order){
	   System.out.println("jhdchjsb");
	   return null;
   }
   
	
	@PostMapping("/createBill/{productId}")
	 BillWrapper createBill(@PathVariable Integer productId, @RequestParam Integer quantity){
		    this.service.createBill(productId,quantity);
		    return null;
	 }
	
	@PostMapping("/kafka")
	public void sendMessage()
	{
		this.orderService.kafka("New to Topic");
	}
	
	
}
