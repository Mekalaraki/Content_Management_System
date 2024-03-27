package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.UserAlreadyExistsByEmailException;
import com.example.cms.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	private ErrorStructure<String> errorStructure;
	
	
	public ApplicationExceptionHandler(ErrorStructure<String> errorStructure) {
		super();
		this.errorStructure = errorStructure;
	}


	private ResponseEntity<ErrorStructure<String>>errorResponse(HttpStatus status,String message,String rootCause) {
		return new ResponseEntity<ErrorStructure<String>>(errorStructure
				.setStatus(status.value())	
				.setMessage(message)
				.setRootCause(rootCause),status);
	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistsByEmailException(UserAlreadyExistsByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,
				ex.getMessage(),"user already exists with the given mail Id");
	}
	@ExceptionHandler
private ResponseEntity<ErrorStructure<String>> handleUserNotFoundException(UserNotFoundException ex){
	return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "user not exist with given id");
}
}
