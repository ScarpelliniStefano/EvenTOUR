package com.scarcolo.eventour.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {
	@JsonProperty("user")
	public String username;
	@JsonProperty("password")
	public String password;
}
