package com.miaoubich.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miaoubich.dto.ProductRequest;
import com.miaoubich.dto.ProductResponse;
import com.miaoubich.entity.Product;
import com.miaoubich.exception.ProductServiceCustomException;
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
				.orElseThrow(() -> new ProductServiceCustomException(
						"The Product with the given id=" + id + " not found!", 
						"PRODUCT_NOT_FOUND_ERROR"));
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		
		return productResponse;
	}
	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce Quantity: {} for id: {}", quantity, productId);
		
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ProductServiceCustomException(
						"Product with given Id not found", "PRODUCT_NOT_FOUND"));
		
		if(product.getQuantity() < quantity) {
			throw new ProductServiceCustomException(
					"Product doesn't have sufficient Quantity",
					"INSUFFICIENT_QUANTITY");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		log.info("Product quantity successfully updated!");
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productResponses = products.stream()
				.map(product -> ProductResponse.builder()
								.productId(product.getProductId())
								.productName(product.getProductName())
								.price(product.getPrice())
								.quantity(product.getQuantity())
								.build()
					)
				.toList();
		
		return productResponses;
	}

}
