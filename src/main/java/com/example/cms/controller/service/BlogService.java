package com.example.cms.controller.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(BlogRequest blogRequest, int blogId);

	ResponseEntity<ResponseStructure<Boolean>> checkForBlog(String title);

}
