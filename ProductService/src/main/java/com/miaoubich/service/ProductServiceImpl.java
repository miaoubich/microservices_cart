package com.miaoubich.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miaoubich.entity.Product;
import com.miaoubich.exception.ProductServiceCustomException;
import com.miaoubich.model.ProductRequest;
import com.miaoubich.model.ProductResponse;
import com.miaoubich.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	
	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding new product ...");
		
		Product product = Product.builder()
					.productName(productRequest.getProductName())
					.quantity(productRequest.getQuantity())
					.price(productRequest.getPrice())
					.build();
		
		productRepository.save(product);
		
		log.info("Product created!");
		
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long id) {
		log.info("Get the product for productId: " + id);
		
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductServiceCustomException("The Product with the given id=" + id + " not found!", "PRODUCT_NOT_FOUND_ERROR"));
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		
		return productResponse;
	}

}
