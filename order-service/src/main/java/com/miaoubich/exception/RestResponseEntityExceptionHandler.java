package com.miaoubich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(OrderCustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(OrderCustomException exception){
		return new ResponseEntity<>(ErrorResponse.builder()
						.errorMessage(exception.getMessage())
						.errorCode(exception.getErrorCode())
						.build(), HttpStatus.valueOf(exception.getStatus()));
	}
}
