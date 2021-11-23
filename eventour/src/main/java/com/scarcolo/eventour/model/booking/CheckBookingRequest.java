package com.scarcolo.eventour.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckBookingRequest {
	@JsonProperty("bookingNr")
	public String bookingNr;
	@JsonProperty("eventId")
	public String eventId;
}
