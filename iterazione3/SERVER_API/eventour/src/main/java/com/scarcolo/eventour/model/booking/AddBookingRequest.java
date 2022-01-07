package com.scarcolo.eventour.model.booking;


import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AddBookingRequest.
 */
public class AddBookingRequest {
	
	/** The user id. */
	@JsonProperty("userId")
	public String userId;
	
	/** The event id. */
	@JsonProperty("eventId")
	public String eventId;
	
	/** The prenoted seat. */
	@JsonProperty("prenotedSeat")
	public Integer prenotedSeat;
	
	
	
}
