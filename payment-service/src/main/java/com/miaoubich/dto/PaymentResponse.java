package com.miaoubich.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

	private long paymentId;
	private long orderId;
	private String paymentMode;
	private String transactionNumber;
	private Instant paymentDate;
	private String paymentStatus;
	private long amount;
}
