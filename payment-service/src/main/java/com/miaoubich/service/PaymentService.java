package com.miaoubich.service;

import com.miaoubich.model.PaymentRequest;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);
}
