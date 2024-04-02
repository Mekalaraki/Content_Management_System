package com.example.cms.entity;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@EntityListeners(value = AuditingEntityListener.class)
@Entity
public class ContributionPanel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int panelId;
	@ManyToMany
	private List<User> contributors = new ArrayList<User>();
	public int getPanelId() {
		return panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	public List<User> getContributors() {
		return contributors;
	}
	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}
	
	
}

