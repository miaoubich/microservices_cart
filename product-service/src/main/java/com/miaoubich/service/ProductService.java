package com.miaoubich.service;

import java.util.List;

import com.miaoubich.model.ProductRequest;
import com.miaoubich.model.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();
	long addProduct(ProductRequest productRequest);
	ProductResponse getProductById(long id);
	void reduceQuantity(long productId, long quantity);
}
