package com.scarcolo.eventour.model.sponsorship;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddSponsorshipRequest {

	@JsonProperty("userId")
	public String userId;
	
	@JsonProperty("eventId")
	public String eventId;
}
