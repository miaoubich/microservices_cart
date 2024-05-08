package com.miaoubich.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miaoubich.dto.PaymentRequest;
import com.miaoubich.dto.PaymentResponse;
import com.miaoubich.service.PaymentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentServiceImpl paymentServiceImpl;

	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
		long paymentId = paymentServiceImpl.doPayment(paymentRequest);
		return new ResponseEntity<>(paymentId, HttpStatus.CREATED);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> getPaymentDetailsForOrderById(@PathVariable(name = "orderId") long orderId) {
		PaymentResponse paymentDetails = paymentServiceImpl.getPaymentDetailsById(orderId);
		return new ResponseEntity<>(paymentDetails, HttpStatus.FOUND);
	}
}
