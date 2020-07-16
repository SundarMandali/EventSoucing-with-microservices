package com.sundar.Orders_service;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.sundar.Orders_service.entity.OrderEvents;
import com.sundar.Orders_service.entity.Orderdetails;
//import com.sundar.Orders_service.events.OrderAcceptedEvent;
//import com.sundar.Orders_service.events.OrderFinishedEvent;
//import com.sundar.Orders_service.events.OrderPlacedEvent;
//import com.sundar.Orders_service.events.OrderStartedEvent;



@Configuration
public class ProducerConfiguration {
	@Bean
	public ProducerFactory<String,OrderEvents> orderEventsFactory() {
		
		
		
		
		Map<String,Object> config=new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092" );
		
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
		
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
		
		
		
		
	}
	@Bean
	public KafkaTemplate<String,OrderEvents>orderEventskafkaTemplate(){
		return new KafkaTemplate<>(orderEventsFactory());
	}
	@Bean
	public ProducerFactory<String,Orderdetails> orderdetailsFactory() {
		
		
		
		
		Map<String,Object> config=new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092" );
		
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
		
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
		
		
		
		
	}
	@Bean
	public KafkaTemplate<String,Orderdetails>orderdetailskafkaTemplate(){
		return new KafkaTemplate<>(orderdetailsFactory());
	}
	
//	
//	
//	@Bean
//	public ProducerFactory<String,OrderStartedEvent> orderStartedEventFactory() {
//		
//		
//		
//		
//		Map<String,Object> config=new HashMap<>();
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092" );
//		
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
//		
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(config);
//		
//		
//		
//		
//	}
//	@Bean
//	public KafkaTemplate<String,OrderStartedEvent>orderStartedEventkafkaTemplate(){
//		return new KafkaTemplate<>(orderStartedEventFactory());
//	}
//	
//	
//	@Bean
//	public ProducerFactory<String,OrderFinishedEvent> orderFinishedEventFactory() {
//		
//		
//		
//		
//		Map<String,Object> config=new HashMap<>();
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092" );
//		
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
//		
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(config);
//		
//		
//}
//	@Bean
//	public KafkaTemplate<String,OrderFinishedEvent>orderFinishedEventkafkaTemplate(){
//		return new KafkaTemplate<>(orderFinishedEventFactory());
//	}
}
