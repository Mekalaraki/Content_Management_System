package com.example.cms.exception;

public class TitleAlphabetsOnlyException extends RuntimeException {
	private String message;
	
	public TitleAlphabetsOnlyException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
	
		return message;
	}

}
