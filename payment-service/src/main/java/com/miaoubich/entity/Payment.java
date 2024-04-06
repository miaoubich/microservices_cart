package com.miaoubich.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Transaction_Details")
public class Payment {

	@Column(name = "payment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;
	@Column(name = "order_id")
	private long orderId;
	@Column(name = "payment_mode")
	private String paymentMode;
	@Column(name = "transaction_number")
	private String transactionNumber;
	@Column(name = "payment_date")
	private Instant paymentDate;
	@Column(name = "payment_status")
	private String paymentStatus;
	private long amount;
}
