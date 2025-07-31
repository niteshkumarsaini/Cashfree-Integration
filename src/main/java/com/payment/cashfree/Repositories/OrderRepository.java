package com.payment.cashfree.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.cashfree.Models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	public Order findByGeneratedOrderId(String generatedOrderId);
	
	
	

}
