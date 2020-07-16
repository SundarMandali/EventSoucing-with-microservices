package com.sundar.Items_service.utils;

import com.sundar.Items_service.entities.Orderdetails;
import com.sundar.Items_service.utils.OrderStatus;

public class ItemsValidation {
	int no_of_oreo_packets=10;
	int no_of_kitkat_chocklates=10;
	int no_of_butter_packets=10;

	public Orderdetails validationMethod(Orderdetails orderdetails) {
		switch(orderdetails.getFlavour())
		{
			case "oreo" : 
				if(no_of_oreo_packets>=orderdetails.getQuantity())
				{
					no_of_oreo_packets=no_of_oreo_packets-(orderdetails.getQuantity());
				
					orderdetails.setStatus(OrderStatus.ItemsValidated);
				}
				break;
			case "kitkat":
				if(no_of_kitkat_chocklates>=orderdetails.getQuantity())
				{
					no_of_kitkat_chocklates=no_of_kitkat_chocklates-(orderdetails.getQuantity());
				
					orderdetails.setStatus(OrderStatus.ItemsValidated);
				}
				
				break;
			case "butter":
				
				if(no_of_butter_packets>=orderdetails.getQuantity())
				{
					no_of_butter_packets=no_of_butter_packets- (orderdetails.getQuantity());
					orderdetails.setStatus(OrderStatus.ItemsValidated);
				}
				
				break;
			default:
				orderdetails.setStatus(OrderStatus.ItemsAreNotAvailable);
		}
		
		return orderdetails;
	}
	

}
