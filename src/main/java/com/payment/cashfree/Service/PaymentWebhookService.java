package com.payment.cashfree.Service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cashfree.pg.model.PaymentWebhook;
import com.payment.cashfree.Models.Card;
import com.payment.cashfree.Models.Message;
import com.payment.cashfree.Models.Transaction;
import com.payment.cashfree.Models.WebhookPaymentResponse;
import com.payment.cashfree.Repositories.CardRepository;
import com.payment.cashfree.Repositories.TransactionRepository;

@Service
public class PaymentWebhookService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CardRepository cardRepository;

	private Logger logger = LoggerFactory.getLogger(PaymentWebhookService.class);

	public Message setTransaction(WebhookPaymentResponse paymentResponse) {

		try {
			// fetch the old transaction status if created
			Transaction transaction = transactionRepository
					.findByOrderId(paymentResponse.getData().getOrder().getOrder_id());
			logger.info("Transaction already exit");

			if (transaction == null) {
				// throw exception
				throw new RuntimeException("No Transaction found.");
			}

			// Making custom login to capture the details from the webhook
			String orderId = paymentResponse.getData().getOrder().getOrder_id();
			String paymentId = paymentResponse.getData().getPayment().getCf_payment_id().toString();
			String status = paymentResponse.getData().getPayment().getPayment_status();
			double value = paymentResponse.getData().getPayment().getPayment_amount();
			BigDecimal pmtAmt = new BigDecimal(String.valueOf(value));
			var method = paymentResponse.getData().getPayment().getPayment_method();
			String paymentMethod = null;
			String paymentProvider = null;
			transaction.setOrderId(orderId);
			transaction.setPaymentId(paymentId);
			transaction.setTransactionId(paymentId);
			transaction.setStatus(status);
			transaction.setTransactionAmount(pmtAmt);
			transaction.setDate(paymentResponse.getData().getPayment().getPayment_time().toString());
			transaction.setCustomerPhone(paymentResponse.getData().getCustomer_details().getCustomer_phone());
			transaction.setTxnDescription(status);

			// finding the payment method
			if (method.getUpi() != null) {
				paymentMethod = "UPI";
				paymentProvider = method.getUpi().getUpi_id();

			}

			// wallet
			else if (method.getApp() != null) {
				paymentMethod = paymentResponse.getData().getPayment().getPayment_group();
				paymentProvider = method.getApp().getProvider();

			}
			// if netbanking
			else if (paymentResponse.getData().getPayment().getPayment_group().equals("net_banking")) {
				paymentMethod = paymentResponse.getData().getPayment().getPayment_group();
				paymentProvider = paymentResponse.getData().getPayment().getPayment_method().getNetbanking()
						.getNetbanking_bank_name();

			}
			// cards
			else if (paymentResponse.getData().getPayment().getPayment_group().equals("debit_card")) {

				// creating and saving
				Card card = Card.builder()
						.card_bank_name(paymentResponse.getData().getPayment().getPayment_method().getCard()
								.getCard_bank_name())
						.card_country(
								paymentResponse.getData().getPayment().getPayment_method().getCard().getCard_country())
						.card_network(
								paymentResponse.getData().getPayment().getPayment_method().getCard().getCard_network())
						.card_number(
								paymentResponse.getData().getPayment().getPayment_method().getCard().getCard_number())
						.card_type(paymentResponse.getData().getPayment().getPayment_method().getCard().getCard_type())
						.customerId(paymentResponse.getData().getCustomer_details().getCustomer_id().toString())
						.build();
				// saving it into the db
				cardRepository.save(card);

				// setting into the transaction
				paymentMethod = paymentResponse.getData().getPayment().getPayment_group();
				paymentProvider = paymentResponse.getData().getPayment().getPayment_method().getCard()
						.getCard_network();

			}

			if (status.equals("FAILED")) {
				// capture the failed reason

				transaction.setTxnDescription(paymentResponse.getData().getError_details().getError_description());
			}

			transaction.setPaymentMethod(paymentMethod);
			transaction.setPaymentProvider(paymentProvider);
			logger.info("Transaction Updated Successfully with the new status..");
//			// saving the updated transaction
			transactionRepository.save(transaction);
			Message message = Message.builder().message("Transaction status updated successfully.")
					.status(HttpStatus.ACCEPTED).build();
			return message;
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return Message.builder().message("Something went wrong..").status(HttpStatus.BAD_GATEWAY).build();
	}

}
