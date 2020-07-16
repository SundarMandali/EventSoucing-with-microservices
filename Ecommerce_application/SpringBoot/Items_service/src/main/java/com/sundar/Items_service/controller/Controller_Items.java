package com.sundar.Items_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.Items_service.entities.OrderEvents;
import com.sundar.Items_service.entities.Orderdetails;
import com.sundar.Items_service.events.ItemsValidationEvent;
import com.sundar.Items_service.events.OrderPlacedEvent;
import com.sundar.Items_service.service.Services;
//import com.sundar.Items_service.repository.OrderDetailsRepository;
//import com.sundar.Items_service.repository.OrderEventRepository;
import com.sundar.Items_service.utils.ItemsValidation;
import com.sundar.Items_service.utils.OrderStatus;


@RestController
@RequestMapping("/items")
public class Controller_Items {
	
	//ItemsValidation itemsValidation=new ItemsValidation();
	@Autowired
	KafkaTemplate<String,OrderEvents> orderEventskafkaTemplate;
	
	@Autowired
	private Services services;
	private static final String topic="events";
	OrderPlacedEvent orderPlacedEventobj=null;
	
	/*
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private OrderEventRepository orderEventRepository;
	
	*/
	@KafkaListener(groupId ="groupapp3",topics="orders",containerFactory ="orderdetailsKafkaListenerContainerFactory")
	public  void getJsonMsgFromTopic(Orderdetails orderdetails)
	{
		
		Integer orderId = orderdetails.getOrderid();
		String orderStatus = orderdetails.getStatus();
		
		if (orderId != null && !orderId.equals("") && orderStatus != null && !orderStatus.equals("") && orderStatus.equals(OrderStatus.OrderPlaced)) {
			System.out.println("Entering into");
			//ItemsValidation itemvalidation = new ItemsValidation();
			//Optional< Orderdetails> optional = orderDetailsRepository.findById(orderId);
		
				orderdetails = services.validationMethod(orderdetails);
				OrderEvents orderEvent = new OrderEvents();
				orderEvent.setId_order(orderId);
				orderEvent.setEvent(orderdetails.getStatus());
				
				
				orderEventskafkaTemplate.send(topic,orderEvent);
				
				
					
					
				
				
				
			}
			
			
		}
		
		
    }
	
	


