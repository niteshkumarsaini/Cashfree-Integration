package com.payment.cashfree.Models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer_transaction")
@Builder
public class Transaction {

	@Id
	private UUID id;
	private String date;
	private BigDecimal transactionAmount;
	private String paymentMethod;
	private String status;
	private String customerPhone;
	private String orderId;
	private BigDecimal orderAmount;
	@Column(name = "payment_id")
	private String paymentId;

	@Column(name = "transaction_id")
	private String transactionId;
	@ManyToOne
	private Customer customer;

	private String paymentProvider;

	private String paymentSessionId;

	private String txnDescription;

}
