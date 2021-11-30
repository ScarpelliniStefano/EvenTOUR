package com.scarcolo.eventour.model.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


// TODO: Auto-generated Javadoc
/**
 * The Class AddUserRequest.
 */
public class AddUserRequest {
	
	/** The name. */
	@JsonProperty("name")
	public String name;
	
	/** The surname. */
	@JsonProperty("surname")
	public String surname;
	
	/** The sex. */
	@JsonProperty("sex")
	public String sex;
	
	/** The date of birth. */
	@JsonProperty("dateOfBirth")
	public LocalDate dateOfBirth;
	
	/** The residence. */
	@JsonProperty("residence")
	public Location residence;
	
	/** The types. */
	@JsonProperty("types")
	public String[] types;
	
	/** The mail. */
	@JsonProperty("mail")
	public String mail;
	
	/** The password. */
	@JsonProperty("password")
	public String password;
	
	/** The username. */
	@JsonProperty("username")
	public String username;

}
