package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.controller.service.BlogPostService;
import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsedto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;

@RestController
public class BlogPostController {
private BlogPostService blogPostService;


@PostMapping("/blogs/{blogId}/blog-posts")
public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(@PathVariable int blogId, @RequestBody BlogPostRequest blogPostRequest){
	return blogPostService.createDraft(blogId,blogPostRequest);
}
}
