package com.payment.cashfree.Service;

import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cashfree.pg.ApiException;
import com.cashfree.pg.ApiResponse;
import com.cashfree.pg.Cashfree;
import com.cashfree.pg.model.CreateOrderRequest;
import com.cashfree.pg.model.CustomerDetails;
import com.cashfree.pg.model.OrderEntity;
import com.payment.cashfree.Models.Customer;
import com.payment.cashfree.Models.Order;
import com.payment.cashfree.Models.OrderResponse;
import com.payment.cashfree.Repositories.CustomerRepository;
import com.payment.cashfree.Repositories.OrderRepository;
import com.payment.cashfree.Webhooks.CashfreeWebhookController;

import jakarta.annotation.PostConstruct;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService {

	@Value("${payment.cashfree.api.AppID}")
	private static String appId;

	@Value("${payment.cashfree.api.SecretKey}")
	private static String secretKey;

	public static Cashfree cashfree = new Cashfree(null, "TEST10713838b34b23e1bd78903d0dbb83831701",
			"cfsk_ma_test_dbc963d4180068aecfd4274908dac187_9808ed7a", null, null, null);

	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public OrderService() {
		// TODO Auto-generated constructor stub

		System.out.println("Initialization is done..");
	}

	public OrderResponse createOrder(Order order) {

		// Creating our Customer
		Customer customer = Customer.builder().custPhone("1234567890").custId(UUID.randomUUID())

				.build();

		// Create the Customer

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId(customer.getCustId().toString());
		customerDetails.setCustomerPhone(customer.getCustPhone());

		order.setCustomer(customer);
		order.setDate(new Date().toLocaleString());
		CreateOrderRequest request = new CreateOrderRequest();
		request.setOrderAmount(order.getOrdAmt());
		request.setOrderCurrency(order.getOrdCurrency());
		request.setCustomerDetails(customerDetails);
		try {
			ApiResponse<OrderEntity> response = cashfree.PGCreateOrder(request, appId, null, null);
			// Setting Response of order

			OrderResponse orderResponse = OrderResponse.builder().customer(customer).date(new Date())
					.ordAmt(order.getOrdAmt()).ordCurrency(order.getOrdCurrency())
					.ordId(response.getData().getOrderId()).paymentSessionId(response.getData().getPaymentSessionId())

					.build();

			logger.info("Payment Link Created " + response.getData().getPaymentSessionId());
			// setting to database
			order.setGeneratedOrderId(orderResponse.getOrdId());
			List<Order> oldOrder = customer.getOrders();
			if (oldOrder == null) {
				oldOrder = new ArrayList<>();

			}
			oldOrder.add(order);
			customer.setOrders(customer.getOrders());
			order.setCustomer(customer);
			// saving the customer
			customerRepository.save(customer);
			orderRepository.save(order);
			orderResponse.setCustomer(customer);
			return orderResponse;

		} catch (Exception e) {
			throw new RuntimeException(e);

		}

	}

}
