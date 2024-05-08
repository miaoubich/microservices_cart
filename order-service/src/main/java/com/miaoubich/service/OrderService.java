package com.miaoubich.service;

import java.util.List;

import com.miaoubich.model.OrderRequest;
import com.miaoubich.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);
	
	List<OrderResponse> getAllOrders();
	
	OrderResponse getOrderDetails(long orderId);
}
