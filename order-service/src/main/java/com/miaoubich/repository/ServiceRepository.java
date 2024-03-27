package com.miaoubich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miaoubich.entity.Order;

public interface ServiceRepository extends JpaRepository<Order, Long>{

}
