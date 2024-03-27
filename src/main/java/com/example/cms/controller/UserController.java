package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.cms.controller.service.UserService;

import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@RestController
public class UserController {
	
	private UserService userService;
	public UserController(UserService userService) {
		super();
		this.userService=userService;
	}
	
	@Operation(description = "The endpoint is used to add a new user to the database",responses = {
			@ApiResponse(responseCode = "200",description = "user added "),
			@ApiResponse(responseCode = "404" ,description = "invalid inputs")
	})
	@PostMapping("/users/register")
	ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest){
		return  userService.registerUser(userRequest);
	}
	@GetMapping("/test")
	public String test() {
		return "hello from cms";
	}
	@Operation(description = "The endpoint is used to delete a user from the database",responses = {
			@ApiResponse(responseCode = "200",description = "user deleted"),
			@ApiResponse(responseCode = "404" ,description = "user id not found")
	})
	@DeleteMapping("/users/{userId}")
	ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable int userId){
		return userService.deleteUserById(userId);
	}
	

}
