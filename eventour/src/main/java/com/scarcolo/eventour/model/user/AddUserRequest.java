package com.scarcolo.eventour.model.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;


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
	public String residence;
	@JsonProperty("types")
	public String[] types;
	@JsonProperty("mail")
	public String mail;
	@JsonProperty("password")
	public String password;

}
