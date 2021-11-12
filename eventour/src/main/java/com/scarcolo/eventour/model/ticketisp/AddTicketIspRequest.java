package com.scarcolo.eventour.model.ticketisp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTicketIspRequest {
	
	@JsonProperty("code")
	public String code;
	
	@JsonProperty("eventId")
	public String eventId;
	
	@JsonProperty("password")
	public String password;
	
	@JsonProperty("fullName")
	public String fullName;
	
}
