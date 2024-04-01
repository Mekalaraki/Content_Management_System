package com.example.cms.exception;

public class TopicsNotSpecifiedException  extends RuntimeException{
	private String message;

	public TopicsNotSpecifiedException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

}
