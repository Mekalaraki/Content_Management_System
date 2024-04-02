package com.example.cms.controller;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.controller.service.ContributionPanelService;
import com.example.cms.responsedto.ContributionPanelResponse;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ContributionPanelController {
private ContributionPanelService contributionPanelService;

public ContributionPanelController(ContributionPanelService contributionPanelService) {
	super();
	this.contributionPanelService = contributionPanelService;
}
@Operation(description = "The endpoint is used to add contributor of user in the database",responses = {
		@ApiResponse(responseCode = "200",description = "blog found "),
		@ApiResponse(responseCode = "404" ,description = "blog not found")
})
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(@PathVariable int userId,@PathVariable int panelId){
		return contributionPanelService.addContributors(userId,panelId);
	}
@Operation(description = "The endpoint is used to check a blog in the database",responses = {
		@ApiResponse(responseCode = "200",description = "blog found "),
		@ApiResponse(responseCode = "404" ,description = "blog not found")
})
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUser(@PathVariable int userId,@PathVariable int panelId){
		return contributionPanelService.removeUser(userId,panelId);
	}
}
