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
@Builder
@Entity
@Table(name = "ORDER_DETAIL")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	@Column(name ="PRODUCT_ID")
	private long productId;
	@Column(name = "QUANTITY")
	private long quantity;
	@Column(name = "ORDER_DATE")
	private Instant orderDate;
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	@Column(name = "AMOUNT")
	private long amount;
}
