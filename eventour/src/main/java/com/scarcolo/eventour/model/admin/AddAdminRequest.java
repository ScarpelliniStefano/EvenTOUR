package com.scarcolo.eventour.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
