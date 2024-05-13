package com.miaoubich.service;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.miaoubich.dto.OrderRequest;
import com.miaoubich.dto.OrderResponse;
import com.miaoubich.dto.external.PaymentResponse;
import com.miaoubich.dto.external.ProductResponse;
import com.miaoubich.entity.Order;
import com.miaoubich.exception.OrderCustomException;
import com.miaoubich.feignclient.PaymentServiceFeign;
import com.miaoubich.feignclient.ProductServiceFeign;
import com.miaoubich.feignclient.request.PaymentRequest;
import com.miaoubich.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final ProductServiceFeign productServiceFeign;
	private final PaymentServiceFeign paymentServiceFeign;
	private final WebClient webClient;
	
	private final String CREATED = "CREATED";

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		//Order Entity -> Save the data with Status Order Created
		//Product Service - Block Products (Reduce the Quantity)
		//Payment Service -> payment Success -> COMPETE, Else CANCELLED
		
		log.info("Placing Order Request {}", orderRequest);
		
		//Product Service -> Reduce the product quantity
		productServiceFeign.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
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
		
		//generate transactionNumber()
		Random ran = new Random();
		long rendomNumber = Math.abs(ran.nextLong());
		String transactionNumber = Long.toString(rendomNumber);
		
		log.info("Call Payment Service to complete the payment!");
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getOrderId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getQuantity())
				.transactionNumber(transactionNumber)
				.build();
		String orderStatus = null;
		try {
			paymentServiceFeign.doPayment(paymentRequest);
			log.info("Payment is success, Order Status changed to Placed");
			orderStatus = "PLACED";
		}
		catch(Exception ex) {
			log.error("ERROR - payment failed, changing orderStatus to FAILED!");
			orderStatus = "PAYMENT_FAILED";
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		log.info("Order placed successfully with Order Id: " + order.getOrderId());
		
		return order.getOrderId();
	}
	
	

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderResponse> OrderResponses = orders.stream().map(order -> OrderResponse.builder()
																				.orderId(order.getOrderId())
																				.productId(order.getProductId())
																				.quantity(order.getQuantity())
																				.orderDate(order.getOrderDate())
																				.orderStatus(order.getOrderStatus())
																				.amount(order.getAmount())
																				.build())
															.toList();
		
		return OrderResponses;
	}



	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get order details for order id: {}", orderId);
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderCustomException("No order found with orderId: orderId",
												"ORDER_NOT_FOUND_ERROR", 404));
		
		log.info("Invoking Product Service to fetch the product detail for the order id: {}", orderId);
		ProductResponse productResponse = webClient.get()
										.uri("http://localhost:8181/products/" + order.getProductId())
										.retrieve()
										.bodyToMono(ProductResponse.class)
										.block();// To convert the call from asynchronous to a synchronous call
		log.info("Fetch the payment information from the payment service.");
		PaymentResponse paymentResponse = webClient.get()
										.uri("http://localhost:8183/payment/order/" + orderId)
										.retrieve()
										.bodyToMono(PaymentResponse.class)
										.block();
		
		log.info("paymentResponse: " + paymentResponse);
		
		ProductResponse productDetails = ProductResponse.builder()
															.productId(productResponse.getProductId())
															.productName(productResponse.getProductName())
															.price(productResponse.getPrice())
															.quantity(productResponse.getQuantity())
															.build();;
		
		log.info("productDetails: " + productDetails);
		
		OrderResponse orderResponse = OrderResponse.builder()
										.orderId(order.getOrderId())
										.amount(order.getAmount())
										.orderDate(order.getOrderDate())
										.orderStatus(order.getOrderStatus())
										.quantity(order.getQuantity())
										.productDetails(productDetails)
										.paymentDetails(paymentResponse)
										.build();
		
		log.info("orderResponse: " + orderResponse);
		
		return orderResponse;
	}

}
