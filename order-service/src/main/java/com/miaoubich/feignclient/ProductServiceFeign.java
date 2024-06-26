package com.miaoubich.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductServiceFeign {

	@PutMapping("/reduceQuantity/{id}")
	ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, 
										@RequestParam long quantity);
}
