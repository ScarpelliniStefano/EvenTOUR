package com.scarcolo.eventour.model.ticketinsp;


import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class AddTicketInspRequest.
 */
public class AddTicketInspRequest {
	
	/** The code. */
	@JsonProperty("code")
	public String code;
	
	/** The full name. */
	@JsonProperty("fullName")
	public String fullName;
	
	/** The event id. */
	@JsonProperty("eventId")
	public String eventId;
	
	/** The password. */
	@JsonProperty("password")
	public String password;


}
