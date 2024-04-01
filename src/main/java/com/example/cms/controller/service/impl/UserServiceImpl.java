package com.example.cms.controller.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.controller.service.UserService;
import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExistsByEmailException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;
@Service
public class UserServiceImpl implements UserService{


	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private ResponseStructure<UserResponse> responseStructure; 

	public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder,
			ResponseStructure<UserResponse> responseStructure) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.responseStructure = responseStructure;
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) { 
		if(userRepo.existsByEmail(userRequest.getEmail())){ 
			throw new UserAlreadyExistsByEmailException("Failed to register user"); 
		} 
		User user=mapToUserEntity(userRequest,new User()); 
		userRepo.save(user); 
		return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value()) 
				.setMessage("User registered Successfully") 
				.setBody(mapToUserResponse(user))); 

	} 

	private UserResponse mapToUserResponse(User user) { 
		return new UserResponse(user.getUserId(),user.getUserName(), 
				user.getEmail(),
				user.getCreatedAt(),
				user.getLastModifiedAt()); 
	} 

	private User mapToUserEntity(UserRequest userRequest, User user) { 

		user.setUserName(userRequest.getUserName()); 
		user.setEmail(userRequest.getEmail()); 
		user.setPassword(passwordEncoder.encode(userRequest.getPassword())); 
		user.setDeleted(false);
		return user; 


	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
		
		return userRepo.findById(userId).map(
				user -> {
					user.setDeleted(true);
					userRepo.save(user);
					
					return ResponseEntity.ok(
							responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("user deleted temporary")
							.setBody(mapToUserResponse(user))
							);
				}).orElseThrow(()-> new UserNotFoundException("user not found"));
	

	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
		
		return userRepo.findById(userId).map(
				user -> {
					userRepo.save(user);
					return ResponseEntity.ok(
							responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("user found")
							.setBody(mapToUserResponse(user))
							);
				}) .orElseThrow(()-> new UserNotFoundException("user not found"));
	}




}
