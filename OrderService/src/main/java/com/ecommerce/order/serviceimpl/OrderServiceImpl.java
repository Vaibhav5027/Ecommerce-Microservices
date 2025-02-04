package com.ecommerce.order.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.common.events.model.OrderEvent;
import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.kafka.OrderEventPublisher;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItems;
import com.ecommerce.order.repo.OrderRepo;
import com.ecommerce.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo repo;
	
	@Autowired 
	private OrderEventPublisher publisher;

	@Override
	public ResponseEntity<String> buyOrder(OrderDTO order) {
		
		Order orderEntityFromObject = OrderEntityFromObject(order);
	try {
		if(order!=null) {
		Order save = repo.save(orderEntityFromObject);
		
		OrderEvent sendOrderData=new OrderEvent();
		sendOrderData.setOrderId(save.getOrderId());
		sendOrderData.setAmount(save.getTotalAmount());
		sendOrderData.setEmail(save.getEmail());
		sendOrderData.setOrderDate(save.getOrderDate());
		sendOrderData.setOrderedProducts(save.getItems().stream().map(item->item.getProductName()).toList());
		System.out.println(sendOrderData);
//		publisher.sendMessage(sendOrderData);


		return new ResponseEntity<String>("{\"message\" :\"Order Placed\"}",HttpStatus.OK);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	
	private Order OrderEntityFromObject(OrderDTO dto) {
		
           Order order=new Order();
           order.setEmail(dto.getEmail());
           order.setTotalAmount(dto.getTotalAmount());
           order.setOrderDate(dto.getOrderDate()); 
           List<OrderItems> orderItems = dto.getItems().stream()
        		    .map(item -> new OrderItems(item.getProductId(), item.getProductName(), item.getPrice(), item.getQuantity()))
        		    .collect(Collectors.toList());
		   order.setItems(orderItems);
		   orderItems.forEach(item->item.setOrder(order));
		   return order;
	}

	@Override
	public void kafka(String string) {
		// TODO Auto-generated method stub
		
	}

	

}
