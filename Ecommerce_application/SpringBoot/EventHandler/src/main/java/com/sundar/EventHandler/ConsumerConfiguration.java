package com.sundar.EventHandler;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.sundar.EventHandler.entities.OrderEvents;
import com.sundar.EventHandler.entities.Orderdetails;





@EnableKafka
@Configuration
public class ConsumerConfiguration {
	@Bean
	public ConsumerFactory<String,String> consumerFactory(){
		
		Map<String,Object> config=new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"groupapp");
		return new DefaultKafkaConsumerFactory<>(config);
	}
 @Bean
 public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
	 
	 ConcurrentKafkaListenerContainerFactory<String,String> factory=new ConcurrentKafkaListenerContainerFactory<String,String>();
	 
	 factory.setConsumerFactory(consumerFactory());
	 factory.setErrorHandler(new SeekToCurrentErrorHandler());
	 return factory;
 }
 
 @Bean
	public ConsumerFactory<String,OrderEvents> userConsumerFactory(){
	 JsonDeserializer<OrderEvents> deserializer = new JsonDeserializer<>(OrderEvents.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
		
		Map<String,Object> config=new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"groupapp7");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),deserializer);
	}
@Bean
public ConcurrentKafkaListenerContainerFactory<String,OrderEvents> orderEventsKafkaListenerContainerFactory(){
	 
	 ConcurrentKafkaListenerContainerFactory<String,OrderEvents> factory=new ConcurrentKafkaListenerContainerFactory<String,OrderEvents>();
	 
	 factory.setConsumerFactory(userConsumerFactory());
	 factory.setErrorHandler(new SeekToCurrentErrorHandler());
	 return factory;
}
@Bean
public ConsumerFactory<String,Orderdetails> userConsumerFactory1(){
 JsonDeserializer<Orderdetails> deserializer = new JsonDeserializer<>(Orderdetails.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);
	
	Map<String,Object> config=new HashMap<>();
	config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
	config.put(ConsumerConfig.GROUP_ID_CONFIG,"groupapp6");
	config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
	config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
	
	return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),deserializer);
}
@Bean
public ConcurrentKafkaListenerContainerFactory<String,Orderdetails> orderdetailsKafkaListenerContainerFactory(){
 
 ConcurrentKafkaListenerContainerFactory<String,Orderdetails> factory=new ConcurrentKafkaListenerContainerFactory<String,Orderdetails>();
 
 factory.setConsumerFactory(userConsumerFactory1());
 factory.setErrorHandler(new SeekToCurrentErrorHandler());
 return factory;
}



 
}
