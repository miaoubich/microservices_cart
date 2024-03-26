package com.miaoubich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miaoubich.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
