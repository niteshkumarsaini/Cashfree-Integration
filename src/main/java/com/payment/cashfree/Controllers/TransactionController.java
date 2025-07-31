package com.payment.cashfree.Controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.cashfree.Models.OrderResponse;
import com.payment.cashfree.Models.PaymentResponse;
import com.payment.cashfree.Models.Transaction;
import com.payment.cashfree.Service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	private Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@ResponseBody
	@PostMapping("/transaction/initiate")
	public ResponseEntity<?> initTransaction(@RequestBody(required = false) OrderResponse orderResponse) {
		logger.info("Transaction initialisation code is running..");
		transactionService.createPendingTransaction(orderResponse);
		return ResponseEntity.ok().build();

	}
}
