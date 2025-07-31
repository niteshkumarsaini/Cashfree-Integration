package com.payment.cashfree.Models;

import java.math.BigDecimal;
import java.util.Date;


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
@Builder
@Data
public class OrderResponse {
	private BigDecimal ordAmt;
	private String ordId;
	private Date date;
	private Customer customer;
	private String ordCurrency;
	private String paymentSessionId;
	
	
}
