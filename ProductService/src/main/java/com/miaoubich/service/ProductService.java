package com.miaoubich.service;

import com.miaoubich.model.ProductRequest;
import com.miaoubich.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);
	ProductResponse getProductById(long id);
}
