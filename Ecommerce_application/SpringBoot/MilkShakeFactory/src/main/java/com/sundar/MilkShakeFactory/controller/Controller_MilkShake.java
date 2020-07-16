package com.sundar.MilkShakeFactory.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.MilkShakeFactory.entities.OrderEvents;
import com.sundar.MilkShakeFactory.entities.Orderdetails;
import com.sundar.MilkShakeFactory.events.MilkShakeFinishedEvent;
import com.sundar.MilkShakeFactory.events.MilkShakePreparationEvent;
import com.sundar.MilkShakeFactory.events.OrderAcceptedEvent;
import com.sundar.MilkShakeFactory.events.OrderStartedEvent;

import com.sundar.MilkShakeFactory.util.OrderStatus;







@Service
public class Controller_MilkShake {
	
	@Autowired
	KafkaTemplate<String,OrderEvents>orderEventskafkaTemplate;

	
	
	private static final String topic="events";
	
	
	
	
	
	@KafkaListener(groupId ="groupapp5",topics="orders",containerFactory = "orderEventsKafkaListenerContainerFactory")
	public  void getJsonMsgFromTopic(OrderEvents orderEvents)
	{
		Integer orderId = orderEvents.getId_order();
		String orderStatus = orderEvents.getEvent();
		if (orderId != null && !orderId.equals("") && orderStatus != null && !orderStatus.equals("")) {
			System.out.println("Listened back into milkshake service");
			
				switch (orderStatus) {
				case OrderStatus.OrderAccepted : 
					{
//						OrderEvents orderEvent = changeEvent(orderId, OrderStatus.MilkShakePrepartionStarted);
//						if (orderEvent != null) 
						orderEvents.setEvent(OrderStatus.MilkShakePrepartionStarted);
						orderEventskafkaTemplate.send(topic,orderEvents);
						break;
					}
				case OrderStatus.OrderStarted : 
				{
//					OrderEvents orderEvent = changeEvent(orderId, OrderStatus.MilkShake_is_Finished);
//					if (orderEvent != null)
					orderEvents.setEvent(OrderStatus.MilkShake_is_Finished);
					orderEventskafkaTemplate.send(topic,orderEvents);
					break;
				}
				
				}
		
		
		
		
		}
		
		
	
		
	}
	
	
	
}
