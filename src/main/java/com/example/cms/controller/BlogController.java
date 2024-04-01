package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.controller.service.BlogService;
import com.example.cms.entity.User;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class BlogController {
	private BlogService blogservice;

	public BlogController(BlogService blogservice) {
		super();
		this.blogservice = blogservice;
	}
	@PostMapping("/users/{userId}/blogs")
	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@Valid @RequestBody BlogRequest blogRequest,@RequestParam int  userId){
		return  blogservice.createBlog(blogRequest,userId);
	}

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@RequestBody BlogRequest blogRequest,@PathVariable int blogId){
		return blogservice.updateBlog(blogRequest,blogId);
	}

}
