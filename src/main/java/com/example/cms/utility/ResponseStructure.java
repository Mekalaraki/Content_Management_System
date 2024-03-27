package com.example.cms.utility;

import org.springframework.stereotype.Component;

@Component
public class ResponseStructure<T> {
	
	
	private int status;
	public int getStatus() {
		return status;
	}
	public ResponseStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public  ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public T getBody() {
		return body;
	}
	public ResponseStructure<T> setBody(T body) {
		this.body = body;
		return this;
	}
	private String message;
	 private T body;
	 
	
	
	 



}
