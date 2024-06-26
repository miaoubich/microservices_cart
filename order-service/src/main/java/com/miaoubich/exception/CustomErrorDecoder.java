package com.miaoubich.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
	@Override

	public Exception decode(String methodKey, Response response) {
		ObjectMapper mapper = new ObjectMapper();

		log.info("::{}", response.request().url());
		log.info("::{}", response.request().headers());

		try {
			ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new OrderCustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(),
					response.status());
		} catch (Exception e) {
			throw new OrderCustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500);
		}
	}

}
