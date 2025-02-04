package com.ecommerce.order.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {
	 private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	@Bean
	public NewTopic topic() {
	return	new NewTopic("order-topic-1",3,(short)1);
	}
	@Bean
	public NewTopic topic1() {
	return	new NewTopic("order-topic",3,(short)1);
	}
	
	@Bean
	public Map<String,Object> producerConfig(){
		Map<String,Object> map=new HashMap<>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return map;
	}
	
	@Bean
	public ProducerFactory<String , Object> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean
	public KafkaTemplate< String, Object> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
