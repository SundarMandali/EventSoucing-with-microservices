package com.sundar.EventHandler.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sundar.EventHandler.entities.Orderdetails;


@Repository
public interface OrderDetailsRepository extends CrudRepository<Orderdetails,Integer>{
	@Query(nativeQuery=true,value="select * FROM order_details od ORDER BY od.orderid DESC limit 1")
	Orderdetails getOrderdetails();
	
}
