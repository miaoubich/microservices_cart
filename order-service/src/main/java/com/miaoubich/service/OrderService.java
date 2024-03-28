package com.miaoubich.service;

import com.miaoubich.model.OrderRequest;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);
}
