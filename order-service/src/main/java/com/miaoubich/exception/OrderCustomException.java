package com.miaoubich.exception;

import lombok.Data;

@Data
public class OrderCustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private int status;
	
	public OrderCustomException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
}
