package com.ecommerce.order.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.common.events.model.OrderEvent;

@Service
public class OrderEventPublisher {
  
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	
	public void sendMessage(OrderEvent orderEvent) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("order-topic", orderEvent);
	}
//	public void sendMessage(String orderEvent) {
//		  System.out.println("event published");
//		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("order-topic-1", orderEvent);
//	}
}
