package com.example.cms.controller.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.controller.service.BlogService;
import com.example.cms.entity.Blog;
import com.example.cms.exception.BlogNotFoundException;
import com.example.cms.exception.TitleAlphabetsOnlyException;
import com.example.cms.exception.TitleAlreadyExistsException;
import com.example.cms.exception.TopicsNotSpecifiedException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService {
	private BlogRepository  blogRepo;
	private ResponseStructure<BlogResponse> responseStructure;
	private UserRepository userRepo;
	private ResponseStructure<Boolean> responsestructure;
	
	

	
	public BlogServiceImpl(BlogRepository blogRepo, ResponseStructure<BlogResponse> responseStructure,
			UserRepository userRepo, ResponseStructure<Boolean> responsestructure2) {
		super();
		this.blogRepo = blogRepo;
		this.responseStructure = responseStructure;
		this.userRepo = userRepo;
		responsestructure = responsestructure2;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest,int userId) {
		return userRepo.findById(userId).map(user->{
			validateBlog(blogRequest);
			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			blogRepo.save(blog);
			userRepo.save(user);
			return ResponseEntity.ok(
					responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("blog")
					.setBody(mapToBlogResponse(blog))
					);
		}).orElseThrow(()-> new UserNotFoundException("failed to create blog"));

	}

	private BlogResponse mapToBlogResponse(Blog blog) {
		
		return new BlogResponse(blog.getBlogId(),
				blog.getTitle(),
		         blog.getTopics(),
		         blog.getAbout());
	}


	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		return blog;
	}
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(BlogRequest blogRequest, int blogId) {
	
		return blogRepo.findById(blogId).map(blog->{
			validateBlog(blogRequest);
			Blog updatedBlog = mapToBlogEntity(blogRequest, new Blog());
			blogRepo.save(updatedBlog);
		
			return ResponseEntity.ok(
					responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("blog")
					.setBody(mapToBlogResponse(blog))
					);
			
		}).orElseThrow(()-> new BlogNotFoundException("failed to update blog"));
	}
	private void validateBlog(BlogRequest blogRequest) {
		if(!blogRequest.getTitle().matches("[a-zA-Z ]+") || blogRequest.getTitle()==null)
			throw new TitleAlphabetsOnlyException("Faild to create blog");

		if(blogRepo.existsByTitle(blogRequest.getTitle()))
			throw new TitleAlreadyExistsException("failed to create blog");
		if(blogRequest.getTopics().length < 1)
			throw new TopicsNotSpecifiedException("failed to create blog");
	}
	@Override
	public ResponseEntity<ResponseStructure<Boolean>> checkForBlog(String title) {
		
		return ResponseEntity.ok(
				responsestructure.setStatus(HttpStatus.OK.value())
				.setMessage("blog found success")
				.setBody(blogRepo.existsByTitle(title)));
				
	}




	}


