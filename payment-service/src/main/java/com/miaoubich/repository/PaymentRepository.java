package com.miaoubich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miaoubich.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Payment findByOrderId(long orderId);

}
