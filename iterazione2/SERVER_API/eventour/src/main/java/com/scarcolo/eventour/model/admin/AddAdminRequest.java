package com.scarcolo.eventour.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AddAdminRequest.
 */
public class AddAdminRequest {
	/** The name. */
	@JsonProperty("name")
	public String name;
	
	/** The surname. */
	@JsonProperty("surname")
	public String surname;
	
	/** The mail. */
	@JsonProperty("mail")
	public String mail;
	
	/** The password. */
	@JsonProperty("password")
	public String password;
	
	/** The role. */
	@JsonProperty("role")
	public String role;
}