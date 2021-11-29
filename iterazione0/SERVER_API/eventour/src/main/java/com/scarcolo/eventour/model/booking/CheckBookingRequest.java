package com.scarcolo.eventour.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckBookingRequest.
 */
public class CheckBookingRequest {
	
	/** The booking nr. */
	@JsonProperty("bookingNr")
	public String bookingNr;
	
	/** The event id. */
	@JsonProperty("eventId")
	public String eventId;
}
