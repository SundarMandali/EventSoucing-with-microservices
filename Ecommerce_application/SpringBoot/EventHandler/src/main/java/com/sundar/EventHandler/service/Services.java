package com.sundar.EventHandler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sundar.EventHandler.entities.OrderEvents;
import com.sundar.EventHandler.entities.Orderdetails;
import com.sundar.EventHandler.repository.OrderDetailsRepository;
import com.sundar.EventHandler.repository.OrderEventRepository;

@Service
public class Services {
	@Autowired
 private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private OrderEventRepository orderEventsRepository;
	
	public Orderdetails getOrderdetails() {
	Orderdetails oe = orderDetailsRepository.getOrderdetails();
	return oe;
	}
	public List<OrderEvents> getEvents(Integer orderid){
		 
		return orderEventsRepository.getOrderEventsById(orderid);
		
	}
	
}
