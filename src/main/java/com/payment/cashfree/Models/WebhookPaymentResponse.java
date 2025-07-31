package com.payment.cashfree.Models;

import com.cashfree.pg.model.Netbanking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class WebhookPaymentResponse {
	private String type;
	private ZonedDateTime event_time;
	private WebhookData data;

	@Data
	public static class WebhookData {
		private Order order;
		private Payment payment;
		private CustomerDetails customer_details;
		private ErrorDetails error_details;
		private PaymentGatewayDetails payment_gateway_details;
		private Object payment_offers; // Use specific class if structure is known

		@Data
		public static class Order {
			private String order_id;
			private Double order_amount;
			private String order_currency;
			private Map<String, String> order_tags;
		}

		@Data
		public static class Payment {
			private Long cf_payment_id;
			private String payment_status;
			private Double payment_amount;
			private String payment_currency;
			private String payment_message;
			private ZonedDateTime payment_time;
			private String bank_reference;
			private String auth_id;
			private PaymentMethod payment_method;
			private String payment_group;

			@Data
			@JsonIgnoreProperties(ignoreUnknown = true) // To ignore any unknown method types in future
			public static class PaymentMethod {
				private UPI upi;
				private App app;
				private Netbanking netbanking;
				private Card card;

				@Data
				public static class UPI {
					private String channel;
					private String upi_id;
				}

				@Data
				public static class Card {
					private String channel;
					private String card_number;
					private String card_network;
					private String card_type;
					private String card_sub_type;
					private String card_country;
					private String card_bank_name;
				}

				@Data
				public static class Netbanking {
					private String channel;
					private String netbanking_bank_code;
					private String netbanking_bank_name;

				}

				@Data
				public static class App {
					private String channel;
					private String upi_id;
					private String provider;
				}
			}
		}

		@Data
		public static class CustomerDetails {
			private String customer_name;
			private String customer_id;
			private String customer_email;
			private String customer_phone;
		}

		@Data
		public static class ErrorDetails {
			private String error_code;
			private String error_description;
			private String error_reason;
			private String error_source;
			private String error_code_raw;
			private String error_description_raw;
			private String error_subcode_raw;
		}

		@Data
		public static class PaymentGatewayDetails {
			private String gateway_name;
			private String gateway_order_id;
			private String gateway_payment_id;
			private String gateway_status_code;
			private String gateway_order_reference_id;
			private String gateway_settlement;
			private String gateway_reference_name;
		}
	}
}
