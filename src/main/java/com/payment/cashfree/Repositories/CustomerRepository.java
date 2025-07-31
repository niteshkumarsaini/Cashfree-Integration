package com.payment.cashfree.Repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.cashfree.Models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,UUID> {

}
