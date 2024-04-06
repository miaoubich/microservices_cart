package com.miaoubich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private long orderId;
	private PaymentMode paymentMode;
	private String transactionNumber;
	private long amount;
}
