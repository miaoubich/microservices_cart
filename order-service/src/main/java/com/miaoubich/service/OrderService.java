package com.miaoubich.service;

import java.util.List;

import com.miaoubich.dto.OrderRequest;
import com.miaoubich.dto.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);
	
	List<OrderResponse> getAllOrders();
	
	OrderResponse getOrderDetails(long orderId);
}
