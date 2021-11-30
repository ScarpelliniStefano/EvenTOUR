package com.scarcolo.eventour.model.sponsorship;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AddSponsorshipRequest.
 */
public class AddSponsorshipRequest {

	/** The user id. */
	@JsonProperty("userId")
	public String userId;
	
	/** The event id. */
	@JsonProperty("eventId")
	public String eventId;
}
