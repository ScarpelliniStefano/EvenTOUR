package com.scarcolo.eventour.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountRequest.
 */
public class AccountRequest {
	
	/** The username. */
	@JsonProperty("user")
	public String username;
	
	/** The password. */
	@JsonProperty("password")
	public String password;
}
