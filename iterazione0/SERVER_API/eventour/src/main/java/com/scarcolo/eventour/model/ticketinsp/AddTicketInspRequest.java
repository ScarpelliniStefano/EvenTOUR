package com.scarcolo.eventour.model.ticketinsp;


import com.fasterxml.jackson.annotation.JsonProperty;


public class AddTicketInspRequest {
	
	@JsonProperty("code")
	public String code;
	@JsonProperty("fullName")
	public String fullName;
	@JsonProperty("eventId")
	public String eventId;
	@JsonProperty("password")
	public String password;


}
