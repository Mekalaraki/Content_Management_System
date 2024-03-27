package com.example.cms.responsedto;

import java.time.LocalDateTime;

public class UserResponse {
	private int userId;
	private String userName;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedBy;
	
	public UserResponse(int userId,String userName,String email, LocalDateTime createdAt, LocalDateTime lastModifiedBy) {
		super();
		this.userId=userId;
		this .userName=userName;
		this.email=email;
		this.createdAt=createdAt;
		this.lastModifiedBy=lastModifiedBy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	
	}
	public LocalDateTime getLastModifiedBy() {
		return lastModifiedBy;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setLastModifiedBy(LocalDateTime lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	
	}
	


