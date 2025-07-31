package com.payment.cashfree.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.payment.cashfree.Models.Order;
import com.payment.cashfree.Models.OrderResponse;
import com.payment.cashfree.Models.PaymentResponse;
import com.payment.cashfree.Models.Transaction;
import com.payment.cashfree.Repositories.OrderRepository;
import com.payment.cashfree.Repositories.TransactionRepository;

@Service
public class TransactionService {

	@Value("${payment.cashfree.api.AppID}")
	private String appId;

	@Value("${payment.cashfree.api.SecretKey}")
	private String secretKey;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private OrderRepository orderRepository;

	private Logger logger = LoggerFactory.getLogger(TransactionService.class);

	// creating transaction with the pending status

	public Transaction createPendingTransaction(OrderResponse orderResponse) {
		// creating pending transaction
		Transaction transaction = Transaction.builder().id(UUID.randomUUID()).customer(orderResponse.getCustomer())
				.orderAmount(orderResponse.getOrdAmt()).orderId(orderResponse.getOrdId())
				.paymentSessionId(orderResponse.getPaymentSessionId()).status("PENDING").build();

		// saving the transaction to the database
		transactionRepository.save(transaction);
		logger.info("Transaction saved successfully with the pending order status..");

		return transaction;

	}

}
