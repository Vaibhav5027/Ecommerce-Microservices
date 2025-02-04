package com.eccomerce.notification_service.serviceImpl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.common.events.model.OrderEvent;

@Service
public class KafkaConsumeEvent {

	@KafkaListener(topics = "order-topic",groupId = "od-new")
	public void consume(@Payload OrderEvent order) {
	try {
		System.out.println("listened");
		System.out.println(order);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
//	@KafkaListener(topics = "order-topic-1",groupId = "OD")
//		public void consume(String order) {
//			try {
//				System.out.println("listened");
//				System.out.println(order);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
}
