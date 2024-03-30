package com.miaoubich.feignconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.miaoubich.exception.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class Config {

	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
}
