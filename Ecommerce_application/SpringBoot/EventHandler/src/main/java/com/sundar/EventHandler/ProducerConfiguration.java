package com.sundar.EventHandler;

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

import com.sundar.EventHandler.entities.OrderEvents;
import com.sundar.EventHandler.entities.Orderdetails;





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
	
//	@Bean
//	public ProducerFactory<String,MilkShakeFinishedEvent> milkShakeFinishedEventFactory() {
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
//	public KafkaTemplate<String,MilkShakeFinishedEvent>milkShakeFinishedEventkafkaTemplate(){
//		return new KafkaTemplate<>(milkShakeFinishedEventFactory());
//	}
	
}
