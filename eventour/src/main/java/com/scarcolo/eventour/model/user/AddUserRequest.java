package com.scarcolo.eventour.model.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


public class AddUserRequest {
	
	@JsonProperty("name")
	public String name;
	@JsonProperty("surname")
	public String surname;
	@JsonProperty("sex")
	public String sex;
	@JsonProperty("dateOfBirth")
	public LocalDate dateOfBirth;
	@JsonProperty("residence")
	public Location residence;
	@JsonProperty("types")
	public String[] types;
	@JsonProperty("mail")
	public String mail;
	@JsonProperty("password")
	public String password;
	@JsonProperty("username")
	public String username;

}
