package com.miaoubich.dto;

import java.time.Instant;

import com.miaoubich.dto.external.PaymentResponse;
import com.miaoubich.dto.external.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

	private long orderId;
	private long productId;
	private long quantity;
	private Instant orderDate;
	private String orderStatus;
	private long amount;
	private ProductResponse productDetails;
	private PaymentResponse paymentDetails;
}
