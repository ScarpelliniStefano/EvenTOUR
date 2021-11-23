package com.scarcolo.eventour.model.booking;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookingRequest {
	@JsonProperty("userId")
	public String userId;
	@JsonProperty("eventId")
	public String eventId;
	@JsonProperty("prenotedSeat")
	public Integer prenotedSeat;
	
}
