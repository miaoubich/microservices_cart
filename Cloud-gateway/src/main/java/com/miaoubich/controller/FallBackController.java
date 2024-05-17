//package com.miaoubich.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FallBackController {
//
//	@RequestMapping("/orderServiceFallBack")
//    public ResponseEntity<String> orderServiceFallback() {
//        return new ResponseEntity<>("Order Service is currently unavailable. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
//    }
//	
//	@GetMapping("productServiceFallBack")
//	public String productServiceFallBack() {
//		return "Product Service is DOWN!";
//	}
//	
//	@GetMapping("paymentServiceFallBack")
//	public String paymentServiceFallBack() {
//		return "Payment Service is DOWN!";
//	}
//}
