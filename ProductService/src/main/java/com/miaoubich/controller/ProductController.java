package com.miaoubich.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miaoubich.model.ProductRequest;
import com.miaoubich.model.ProductResponse;
import com.miaoubich.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductServiceImpl productService;
	
	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		long productId = productService.addProduct(productRequest);
		return new ResponseEntity<>(productId, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long id){
		ProductResponse productResponse = productService.getProductById(id);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.FOUND);
	}
}