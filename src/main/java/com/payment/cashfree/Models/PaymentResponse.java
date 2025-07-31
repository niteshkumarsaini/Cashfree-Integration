package com.payment.cashfree.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse {

    @JsonProperty("auth_id")
    private String authId;

    private String authorization;

    @JsonProperty("bank_reference")
    private String bankReference;

    @JsonProperty("cf_payment_id")
    private Long cfPaymentId;

    private String entity;

    @JsonProperty("error_details")
    private String errorDetails;

    @JsonProperty("is_captured")
    private boolean isCaptured;

    @JsonProperty("order_amount")
    private Double orderAmount;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("payment_amount")
    private Double paymentAmount;

    @JsonProperty("payment_completion_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime paymentCompletionTime;

    @JsonProperty("payment_currency")
    private String paymentCurrency;

    @JsonProperty("payment_gateway_details")
    private PaymentGatewayDetails paymentGatewayDetails;

    @JsonProperty("payment_group")
    private String paymentGroup;

    @JsonProperty("payment_message")
    private String paymentMessage;

    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("payment_offers")
    private Object paymentOffers;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("payment_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime paymentTime;

    @Data
    @NoArgsConstructor
    public static class PaymentGatewayDetails {
        @JsonProperty("gateway_name")
        private String gatewayName;

        @JsonProperty("gateway_order_id")
        private String gatewayOrderId;

        @JsonProperty("gateway_payment_id")
        private String gatewayPaymentId;

        @JsonProperty("gateway_order_reference_id")
        private String gatewayOrderReferenceId;

        @JsonProperty("gateway_status_code")
        private String gatewayStatusCode;

        @JsonProperty("gateway_settlement")
        private String gatewaySettlement;
    }

    @Data
    @NoArgsConstructor
    public static class PaymentMethod {
        private Upi upi;
        private App app;
        private Card card;
        private Netbanking netbanking;

        @JsonProperty("bank_transfer")
        private BankTransfer bankTransfer;

        @Data
        @NoArgsConstructor
        public static class Upi {
            private String channel;

            @JsonProperty("upi_id")
            private String upiId;
        }

        @Data
        @NoArgsConstructor
        public static class App {
            private String channel;
            private String phone;
            private String provider;
        }

        @Data
        @NoArgsConstructor
        public static class Card {
            @JsonProperty("card_number")
            private String cardNumber;

            @JsonProperty("card_network")
            private String cardNetwork;

            @JsonProperty("card_type")
            private String cardType;
        }

        @Data
        @NoArgsConstructor
        public static class Netbanking {
            private String channel;

            @JsonProperty("netbanking_bank_code")
            private String bankCode;

            @JsonProperty("netbanking_bank_name")
            private String bankName;
        }

        @Data
        @NoArgsConstructor
        public static class BankTransfer {
            @JsonProperty("sender_name")
            private String senderName;

            @JsonProperty("sender_account_number")
            private String senderAccountNumber;

            private String ifsc;
        }
    }
}
