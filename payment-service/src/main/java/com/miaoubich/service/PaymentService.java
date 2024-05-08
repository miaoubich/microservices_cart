package com.miaoubich.service;

import com.miaoubich.dto.PaymentRequest;
import com.miaoubich.dto.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);
	
	PaymentResponse getPaymentDetailsById(long orderId);
}
