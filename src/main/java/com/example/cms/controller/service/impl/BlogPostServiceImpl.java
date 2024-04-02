package com.example.cms.controller.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.controller.service.BlogPostService;
import com.example.cms.entity.Blog;
import com.example.cms.entity.BlogPost;
import com.example.cms.exception.BlogNotFoundException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsedto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;
@Service
public class BlogPostServiceImpl implements BlogPostService {
	private UserRepository userRepository;

	private BlogPostRepository blogPostRepo;

	private BlogRepository blogRepo;

	private ResponseStructure<BlogPostResponse> responseStructure;	
	private ContributionPanelRepository contributionRepo;
	
	public BlogPostServiceImpl(UserRepository userRepository, BlogPostRepository blogPostRepo, BlogRepository blogRepo,
			ResponseStructure<BlogPostResponse> responseStructure, ContributionPanelRepository contributionRepo) {
		super();
		this.userRepository = userRepository;
		this.blogPostRepo = blogPostRepo;
		this.blogRepo = blogRepo;
		this.responseStructure = responseStructure;
		this.contributionRepo = contributionRepo;
	}


	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(int blogId,
			BlogPostRequest blogPostRequest) {
		
		return blogRepo.findById(blogId).map(blog->{
			if(!validateUser(blog)) {
				throw new IllegalAccessRequestException("failes to create draft");
			}
			BlogPost blogPost = mapToBlogPostEntity(blogPostRequest, new BlogPost());
			blogPost.setBlog(blog);
			BlogPost post = blogPostRepo.save(blogPost);
			blog.getBlogPosts().add(post);
			blogRepo.save(blog);
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("blockpost drafted successfully")
					.setBody(mapToBlogPostResponse(blogPost))
					);
		}).orElseThrow(()-> new BlogNotFoundException("failed to create blog"));
	}
		

	private BlogPost mapToBlogPostEntity(BlogPostRequest blogPostRequest, BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		blogPost.setSeoTitle(blogPostRequest.getSeoTitle());
		blogPost.setSeoDescription(blogPostRequest.getSeoDescription());
		blogPost.setSeoTopics(blogPostRequest.getSeoTopics());
		return blogPost;
	}

	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return new BlogPostResponse(
				blogPost.getBlogPostId(),
				blogPost.getTitle(),
				blogPost.getSubTitle(),
				blogPost.getSummary(),
				blogPost.getPostType(),
				blogPost.getSeoTitle(),
				blogPost.getSeoDescription(),
				blogPost.getSeoTopics()
				);

	}
	private Boolean validateUser(Blog blog) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(user->
		email.equals(blog.getUser().getEmail())||contributionRepo.existsByPanelIdAndContributors(blog.getContributionPanel().getPanelId(),user)).orElse(false);
		
	}
}
