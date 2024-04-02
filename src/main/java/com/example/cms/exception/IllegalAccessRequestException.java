package com.example.cms.exception;

public class IllegalAccessRequestException extends RuntimeException {
	private String message;

	public IllegalAccessRequestException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		
		return message;
	}
	

}
