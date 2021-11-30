package com.scarcolo.eventour.model.sponsorship;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class EditSponsorshipRequest.
 */
public class EditSponsorshipRequest extends AddSponsorshipRequest{
	
	/** The id. */
	@JsonProperty("id")
	public String id;

}
