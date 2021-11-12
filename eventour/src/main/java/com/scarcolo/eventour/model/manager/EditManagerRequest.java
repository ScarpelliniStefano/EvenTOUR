package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditManagerRequest extends AddManagerRequest {

	@JsonProperty("id")
	public String id;
	
}
