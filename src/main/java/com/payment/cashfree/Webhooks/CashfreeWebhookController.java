package com.payment.cashfree.Webhooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.payment.cashfree.Models.WebhookPaymentResponse;
import com.payment.cashfree.Service.PaymentWebhookService;

@RestController
@RequestMapping("/cashfree")
public class CashfreeWebhookController {

	private Logger logger = LoggerFactory.getLogger(CashfreeWebhookController.class);

	@Autowired
	private PaymentWebhookService webhookService;

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(@RequestBody String payload,
			@RequestHeader(value = "x-cf-signature", required = false) String signature) {
		logger.info("Webhook Received:\n" + payload);
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(payload);
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			// map the json to object
			WebhookPaymentResponse webhookPayment = mapper.treeToValue(json, WebhookPaymentResponse.class);
			// setting the transaction details based on the Webhook to the database
			webhookService.setTransaction(webhookPayment);

			return ResponseEntity.ok("Received");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON");
		}
	}
}