package com.payment.cashfree.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card {
	@Id
	@GeneratedValue
	private UUID id;
	private String channel;
	private String card_number;
	private String card_network;
	private String card_type;
	private String card_country;
	private String card_bank_name;
	private String customerId;

}
