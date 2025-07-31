package com.payment.cashfree.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payment.cashfree.Models.Card;


@Repository
public interface CardRepository extends CrudRepository<Card,UUID> {
	

}
