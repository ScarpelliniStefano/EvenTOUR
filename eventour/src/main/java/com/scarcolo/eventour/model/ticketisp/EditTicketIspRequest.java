package com.scarcolo.eventour.model.ticketisp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditTicketIspRequest extends AddTicketIspRequest {
	
	@JsonProperty("id")
	public String id;
	
}
