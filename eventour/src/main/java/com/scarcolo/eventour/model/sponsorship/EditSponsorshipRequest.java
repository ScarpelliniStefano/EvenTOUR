package com.scarcolo.eventour.model.sponsorship;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditSponsorshipRequest extends AddSponsorshipRequest{
	
	@JsonProperty("id")
	public String id;

}
