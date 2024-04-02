package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class BlogController {
	private BlogService blogservice;

	public BlogController(BlogService blogservice) {
		super();
		this.blogservice = blogservice;
	}
	@Operation(description = "The endpoint is used to add a new blog to the database",responses = {
			@ApiResponse(responseCode = "200",description = "blog created successfully "),
			@ApiResponse(responseCode = "400" ,description = "invalid inputs")
	})
	@PostMapping("/users/{userId}/blogs")
	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@Valid @RequestBody BlogRequest blogRequest,@RequestParam int  userId){
		return  blogservice.createBlog(blogRequest,userId);
	}
	@Operation(description = "The endpoint is used to update a blog in the database",responses = {
			@ApiResponse(responseCode = "200",description = "blog updated"),
			@ApiResponse(responseCode = "404" ,description = "Failed to update blog")
	})
@PutMapping("/blogs/{blogId}")
	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@RequestBody BlogRequest blogRequest,@PathVariable int blogId){
		return blogservice.updateBlog(blogRequest,blogId);
	}
	@Operation(description = "The endpoint is used to check a blog in the database",responses = {
			@ApiResponse(responseCode = "200",description = "blog found "),
			@ApiResponse(responseCode = "404" ,description = "blog not found")
	})
	@GetMapping("/titles/{title}/blogs")
ResponseEntity<ResponseStructure<Boolean>> checkForBlog(@PathVariable String title){
	return blogservice.checkForBlog(title);
}
	@Operation(description = "The endpoint is used to find a blog in the database",responses = {
			@ApiResponse(responseCode = "200",description = "blog found successfully"),
			@ApiResponse(responseCode = "404" ,description = "blog not found")
	})
@GetMapping("/blogs/{blogId}")
ResponseEntity<ResponseStructure<BlogResponse>> findByBlogId(@PathVariable int blogId){
	return blogservice.findByBlogId(blogId);
}
	
}
