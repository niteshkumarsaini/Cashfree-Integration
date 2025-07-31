package com.payment.cashfree.Repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.cashfree.Models.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,UUID>{

	public Transaction findByOrderId(String orderId);
	
	
	
	
	
	
}
