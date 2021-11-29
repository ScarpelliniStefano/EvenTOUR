package com.scarcolo.eventour.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponse {
	@JsonProperty("typeUser")
	private String typeUser;
	@JsonProperty("user")
	private Object user;
	
	
	public AccountResponse(String typeUser, Object user) {
		this.typeUser = typeUser;
		this.user = user;
	}
	public String getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	
}
