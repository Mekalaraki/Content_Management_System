package com.example.cms.exception;

public class BlogNotFoundException  extends RuntimeException{
	private String message;

	public BlogNotFoundException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

}
