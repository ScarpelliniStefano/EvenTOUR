package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddManagerRequest {
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("surname")
	public String surname;
	
	@JsonProperty("dateOfBirth")
	public String dateOfBirth;
	
	@JsonProperty("residence")
	public String residence;
	
	@JsonProperty("codicePIVA")
	public String codicePIVA;
	
	@JsonProperty("RagioneSociale")
	public String ragioneSociale;
	
	@JsonProperty("mail")
	public String mail;
	
	@JsonProperty("password")
	public String password;
	
}
