package com.sundar.Orders_service.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sundar.Orders_service.entity.OrderEvents;
import com.sundar.Orders_service.entity.Orderdetails;
import com.sundar.Orders_service.events.ItemsValidationEvent;
//
//import com.sundar.Orders_service.repository.OrderDetailsRepository;
//import com.sundar.Orders_service.repository.OrderEventRepository;
import com.sundar.Orders_service.utils.OrderStatus;

@CrossOrigin
@RestController

@RequestMapping("kafka")
public class OrderController {
	@Autowired
	KafkaTemplate<String,OrderEvents> orderEventskafkaTemplate;
	@Autowired
	KafkaTemplate<String,Orderdetails> orderdetailskafkaTemplate;
//
//	@Autowired
//	private OrderDetailsRepository orderDetailsRepository;
//	@Autowired
//	private OrderEventRepository orderEventRepository;
	
	ItemsValidationEvent itemsValidationEventobj=null;
	
	private static final String topic="events";
	@PostMapping("/placeorder")
	public String post(@RequestBody Orderdetails orderdetails)
	{
//		orderdetails.setStatus(OrderStatus.OrderPlaced);
//		orderdetails = orderDetailsRepository.save(orderdetails);
//		OrderEvents oe = new OrderEvents();
//		oe.setId_order(orderdetails.getOrderid());
//		oe.setEvent(OrderStatus.OrderPlaced);
//		orderEventRepository.save(oe);
//		orderEventskafkaTemplate.send(topic, oe);
		orderdetails.setStatus(OrderStatus.OrderPlaced);
		orderdetailskafkaTemplate.send("events",orderdetails);
		return "Published OrderPlacedEvent";
		
	}
	
	
	

	@KafkaListener(groupId ="groupapp4",topics="orders",containerFactory = "orderEventsKafkaListenerContainerFactory")
	public  void getJsonMsgFromTopic(OrderEvents orderEvents)
	{
		

		
		Integer orderId = orderEvents.getId_order();
		String orderStatus = orderEvents.getEvent();
		
		if (orderId != null && !orderId.equals("") && orderStatus != null && !orderStatus.equals("")) {
			System.out.println("Listened back into order service");
			
				switch (orderStatus) {
				case OrderStatus.ItemsValidated : 
					{
//						OrderEvents orderEvent = changeEvent(orderId, OrderStatus.OrderAccepted);
//						if (orderEvent != null) 
//							orderEventskafkaTemplate.send(topic,orderEvent);
						
						orderEvents.setEvent(OrderStatus.OrderAccepted);
						orderEventskafkaTemplate.send(topic,orderEvents);
						break;
					}
				case OrderStatus.MilkShakePrepartionStarted : 
				{
//					OrderEvents orderEvent = changeEvent(orderId, OrderStatus.OrderStarted);
//					if (orderEvent != null)
//						orderEventskafkaTemplate.send(topic,orderEvent);
					
					orderEvents.setEvent(OrderStatus.OrderStarted);
					orderEventskafkaTemplate.send(topic,orderEvents);
					break;
				}
				case OrderStatus.MilkShake_is_Finished : 
				{
//					OrderEvents orderEvent = changeEvent(orderId, OrderStatus.OrderDelivered);
//					if (orderEvent != null)
//						orderEventskafkaTemplate.send(topic,orderEvent);
					orderEvents.setEvent(OrderStatus.OrderDelivered);
					orderEventskafkaTemplate.send(topic,orderEvents);
					
					break;
				}
				
				}
		
		
		
		
		}

	}
}

