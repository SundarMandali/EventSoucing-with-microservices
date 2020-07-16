package com.sundar.Items_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundar.Items_service.entities.Itemdetails;
import com.sundar.Items_service.entities.Orderdetails;
import com.sundar.Items_service.repository.ItemDetailsRepository;
import com.sundar.Items_service.utils.OrderStatus;

@Service
public class Services {
	@Autowired
	private ItemDetailsRepository itemDetailsRepository;

	public Orderdetails validationMethod(Orderdetails orderdetails) {
		if (orderdetails != null && !orderdetails.equals("") ) {
			Itemdetails item=itemDetailsRepository.getItemdetailsByFlavour(orderdetails.getFlavour());
			if(item.getQuantity() >= orderdetails.getQuantity()) {
				Integer quantity = item.getQuantity();
				quantity -= orderdetails.getQuantity();
				item.setQuantity(quantity);
				itemDetailsRepository.save(item);
				orderdetails.setStatus(OrderStatus.ItemsValidated);
				
			}else
			{
				orderdetails.setStatus(OrderStatus.ItemsAreNotAvailable);
			}
					
			
			
		}
		
		return orderdetails;
	}
	

	
	
	
	

}
