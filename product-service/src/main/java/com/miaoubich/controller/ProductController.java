package com.miaoubich.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miaoubich.dto.ProductRequest;
import com.miaoubich.dto.ProductResponse;
import com.miaoubich.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductServiceImpl productService;
	
	@GetMapping
	public ResponseEntity<?> displayAllProducts(){
		List<ProductResponse> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
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
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, 
										@RequestParam long quantity){
		productService.reduceQuantity(productId, quantity);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
