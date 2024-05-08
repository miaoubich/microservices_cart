package com.miaoubich.service;

import java.util.List;

import com.miaoubich.dto.ProductRequest;
import com.miaoubich.dto.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();
	long addProduct(ProductRequest productRequest);
	ProductResponse getProductById(long id);
	void reduceQuantity(long productId, long quantity);
}
