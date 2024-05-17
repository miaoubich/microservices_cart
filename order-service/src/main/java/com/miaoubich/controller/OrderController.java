package com.miaoubich.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miaoubich.dto.OrderRequest;
import com.miaoubich.dto.OrderResponse;
import com.miaoubich.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/place-order")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
		long orderId = orderService.placeOrder(orderRequest);
		log.info("Order Id: " + orderId);

		return new ResponseEntity<Long>(orderId, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getAllOrders() {
		List<OrderResponse> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	@CircuitBreaker(name = "order", fallbackMethod = "orderServiceFallBack")
	public ResponseEntity<?> getOrderDetailsById(@PathVariable(name = "id") long id) {
		OrderResponse order = orderService.getOrderDetails(id);
		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}

	// Fallback method must match the original method's parameters and return type
	public ResponseEntity<?> orderServiceFallBack(Throwable throwable) {
		log.error("Fallback triggered due to: ", throwable);
		return new ResponseEntity<>("Order Service is DOWN!", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
