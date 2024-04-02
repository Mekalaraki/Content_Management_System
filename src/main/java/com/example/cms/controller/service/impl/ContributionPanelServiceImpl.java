package com.example.cms.controller.service.impl;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.controller.service.ContributionPanelService;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.responsedto.ContributionPanelResponse;
import com.example.cms.utility.ResponseStructure;
@Service
public class ContributionPanelServiceImpl implements ContributionPanelService {

	private UserRepository userRepo;

	private BlogRepository blogRepo;

	private ContributionPanelRepository contributionPanelRepo;

	private ResponseStructure<ContributionPanelResponse> responseStructure;
	
	
	public ContributionPanelServiceImpl(UserRepository userRepo, BlogRepository blogRepo,
			ContributionPanelRepository contributionPanelRepo,
			ResponseStructure<ContributionPanelResponse> responseStructure) {
		super();
		this.userRepo = userRepo;
		this.blogRepo = blogRepo;
		this.contributionPanelRepo = contributionPanelRepo;
		this.responseStructure = responseStructure;
	}
	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		System.out.println(email);
		
		return	userRepo.findByEmail(email).map(owner->{
			return contributionPanelRepo.findById(panelId).map(panel->{
				if(blogRepo.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("failed to add contributor");
				return userRepo.findById(userId).map(contributor->{
					panel.getContributors().add(contributor);
					contributionPanelRepo.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor added succesffuly")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundException("User not found"));
			}).orElseThrow(()-> new PanelNotFoundByIdException("Panel not found"));
		}).get();

	}
	private ContributionPanelResponse mapToContributionPanelResponse(ContributionPanel panel) {
		
		return new ContributionPanelResponse(panel.getPanelId());
	}
	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUser(int userId, int panelId) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return	userRepo.findByEmail(email).map(owner->{
			return contributionPanelRepo.findById(panelId).map(panel->{
				if(!blogRepo.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("failed to remove contributor");
				return userRepo.findById(userId).map(contributor->{
					panel.getContributors().remove(contributor);
					contributionPanelRepo.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor removed succesffuly")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundException("User not found"));
			}).orElseThrow(()-> new PanelNotFoundByIdException("Panel not found"));
		}).get();
	};
	}

