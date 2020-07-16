package com.sundar.EventHandler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.sundar.EventHandler.entities.OrderEvents;

@Repository
public interface OrderEventRepository extends CrudRepository<OrderEvents,Integer>{
	@Query("select orderEvents from OrderEvents orderEvents where orderEvents.id_order=:orderid")
	List<OrderEvents> getOrderEventsById(@Param("orderid") Integer orderid);
}
