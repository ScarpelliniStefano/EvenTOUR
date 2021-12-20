package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class AddManagerRequest.
 */
public class AddManagerRequest {
	
	/** The name. */
	@JsonProperty("name")
	public String name;
	
	/** The surname. */
	@JsonProperty("surname")
	public String surname;
	
	/** The date of birth. */
	@JsonProperty("dateOfBirth")
	public LocalDate dateOfBirth;
	
	/** The residence. */
	@JsonProperty("residence")
	public Location residence;
	
	/** The codice PIVA. */
	@JsonProperty("codicePIVA")
	public String codicePIVA;
	
	/** The ragione sociale. */
	@JsonProperty("ragioneSociale")
	public String ragioneSociale;
	
	/** The mail. */
	@JsonProperty("mail")
	public String mail;
	
	/** The password. */
	@JsonProperty("password")
	public String password;
	
	
}
