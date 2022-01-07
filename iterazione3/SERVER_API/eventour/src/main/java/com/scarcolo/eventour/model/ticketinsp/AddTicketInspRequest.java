package com.scarcolo.eventour.model.ticketinsp;


import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class AddTicketInspRequest.
 */
public class AddTicketInspRequest {
	
	/** The full name. */
	@JsonProperty("fullName")
	public String fullName;
	
	/** The event id. */
	@JsonProperty("eventId")
	public String eventId;


}
