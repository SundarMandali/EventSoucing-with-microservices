package com.sundar.EventHandler.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sundar.EventHandler.entities.OrderEvents;
import com.sundar.EventHandler.entities.Orderdetails;
import com.sundar.EventHandler.repository.OrderDetailsRepository;
import com.sundar.EventHandler.repository.OrderEventRepository;
import com.sundar.EventHandler.service.Services;
import com.sundar.EventHandler.util.OrderStatus;






@CrossOrigin
@RestController
@RequestMapping("/orderhistory")
public class EventHandleController {
	
	@Autowired
	KafkaTemplate<String,OrderEvents>orderEventskafkaTemplate;
	@Autowired
	KafkaTemplate<String,Orderdetails>orderdetailskafkaTemplate;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private OrderEventRepository orderEventRepository;
	
	@Autowired
	private Services services;
	private static final String topic="orders";
	
	public OrderEvents changeEvent(Integer orderId,String status) {
		
		Optional< Orderdetails> optional = orderDetailsRepository.findById(orderId);
		Orderdetails orderdetails = null;
		if(optional.isPresent())  {
			
			orderdetails = optional.get();
			orderdetails.setStatus(status);
			orderdetails = orderDetailsRepository.save(orderdetails);
			OrderEvents orderEvent = new OrderEvents();
			orderEvent.setId_order(orderId);
			orderEvent.setEvent(status);
			orderEvent.setTime(new Date(System.currentTimeMillis()));
			orderEventRepository.save(orderEvent);
			
			return orderEvent;
		}
		return null;
	
	}
	@KafkaListener(groupId ="groupapp6",topics="events",containerFactory = "orderdetailsKafkaListenerContainerFactory")
	public void getOrderdetails(Orderdetails orderdetails)
	{
		if(orderdetails!=null && !orderdetails.equals("")) {
			String orderStatus = orderdetails.getStatus();
			if(orderStatus != null && !orderStatus.equals("")) {
				orderdetails.setStatus(OrderStatus.OrderPlaced);
				orderdetails = orderDetailsRepository.save(orderdetails);
				OrderEvents orderEvent = changeEvent(orderdetails.getOrderid(), OrderStatus.OrderPlaced);
//				System.out.println(orderEvent.getEvent());
				orderdetailskafkaTemplate.send("orders",orderdetails);
			}
		}
	}
	
	
	@KafkaListener(groupId ="groupapp7",topics="events",containerFactory = "orderEventsKafkaListenerContainerFactory")
	public  void getJsonMsgFromTopic(OrderEvents orderEvents)
	{
		Integer orderId = orderEvents.getId_order();
		String orderStatus = orderEvents.getEvent();
		if (orderId != null && !orderId.equals("") && orderStatus != null && !orderStatus.equals("")) {
			System.out.println("Listened back into milkshake service");
			
			OrderEvents orderEvent = changeEvent(orderId, orderStatus);
			if (orderEvent != null)
				orderEventskafkaTemplate.send(topic,orderEvent);
			
		
		}
		
	}
	
	@GetMapping("/orderdetails")
	public Orderdetails getOrderdetails() {
		return services.getOrderdetails();
		
	}
	
	@GetMapping("/listevnts/{id_order}")
	public List<OrderEvents> getEvents(@PathVariable("id_order") Integer orderid)
	{
		return services.getEvents(orderid);
	}
	
	@GetMapping("/orderCompleteDetails")
	public List<OrderEvents> getOrderCompleteDetails() {
		return services.getEvents( services.getOrderdetails().getOrderid());
		
	}
	
}
