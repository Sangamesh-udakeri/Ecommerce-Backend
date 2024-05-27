package com.project.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity

public class AuthenticationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String  token;
	private Date createdDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	User user;
	public AuthenticationToken(User user){
		this.user=user;
		this.createdDate=new Date();
		this.token=UUID.randomUUID().toString();
	}
	public AuthenticationToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
