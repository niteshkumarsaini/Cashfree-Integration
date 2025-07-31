package com.payment.cashfree.Models;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer_order")  // <-- Rename the table here
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal ordAmt;
    

    private String generatedOrderId;
    
    
    private String ordCurrency;

    @ManyToOne
    private Customer customer;
    
    
    private String date;
    
}
