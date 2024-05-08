package com.miaoubich.service;

import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miaoubich.dto.PaymentResponse;
import com.miaoubich.dto.PaymentRequest;
import com.miaoubich.entity.Payment;
import com.miaoubich.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{

	private final PaymentRepository paymentRepository;
	private String SUCCESS = "Success";

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording payment details: " + paymentRequest);
		
		Payment payment = Payment.builder()
				.amount(paymentRequest.getAmount())
				.orderId(paymentRequest.getOrderId())
				.paymentDate(Instant.now())
				.paymentStatus(SUCCESS)
				.transactionNumber(paymentRequest.getTransactionNumber())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.build();
		
		paymentRepository.save(payment);
		
		log.info("Transaction completed successfully with Id={}", payment.getPaymentId());
		
		return payment.getPaymentId();
	}

	@Override
	public PaymentResponse getPaymentDetailsById(long orderId) {
		log.info("Fetching payment details for the Order with id: {}", orderId);
		Payment paymentDetails = paymentRepository.findByOrderId(orderId);
		
		PaymentResponse paymentResponseDetails = new PaymentResponse();
		BeanUtils.copyProperties(paymentDetails, paymentResponseDetails);
		
		return paymentResponseDetails;
	}
}
