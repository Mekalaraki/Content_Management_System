package com.example.cms.exception;

public class TitleAlreadyExistsException extends RuntimeException {
	private String message;
	
	public TitleAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
