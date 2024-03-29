package com.miaoubich.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.miaoubich.entity.Order;
import com.miaoubich.feignclient.ProductService;
import com.miaoubich.model.OrderRequest;
import com.miaoubich.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final ProductService productService;
	private final String CREATED = "CREATED";

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		//Payment Service -> payment Success -> COMPETE, Else CANCELLED
		
		log.info("Placing Order Request " + orderRequest);
		
		//Product Service -> Reduce the product quantity
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		log.info("Placing Order with status CREATED");
		Order order = Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus(CREATED)
				.productId(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		
		//Order Entity -> Save the data with Status Created
		order = orderRepository.save(order);
		
		log.info("Order placed successfully with Order Id: " + order.getOrderId());
		
		
		
		
		return order.getOrderId();
	}

}
