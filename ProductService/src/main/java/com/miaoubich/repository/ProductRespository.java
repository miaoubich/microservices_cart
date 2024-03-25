package com.miaoubich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miaoubich.entity.Product;

public interface ProductRespository extends JpaRepository<Product, Long>{

}
