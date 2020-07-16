package com.sundar.Items_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sundar.Items_service.entities.Itemdetails;




@Repository
public interface ItemDetailsRepository extends CrudRepository<Itemdetails,Integer>{
	@Query("select item from Itemdetails item where item.flavour=:flavour")
	Itemdetails getItemdetailsByFlavour(@Param("flavour") String flavour );

}
