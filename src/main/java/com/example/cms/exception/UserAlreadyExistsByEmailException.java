package com.example.cms.exception;

public class UserAlreadyExistsByEmailException extends RuntimeException {
private String message;
public UserAlreadyExistsByEmailException(String message){
	this.message=message;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
