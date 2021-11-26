package com.scarcolo.eventour.model.event;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


public class AddEventRequest {
	
	@JsonProperty("title")
	public String title;
	@JsonProperty("description")
	public String description;
	@JsonProperty("location")
	public Location location;
	@JsonProperty("types")
	public String[] types;
	@JsonProperty("dataOra")
	public LocalDateTime dataOra;
	@JsonProperty("managerId")
	public String managerId;
	@JsonProperty("urlImage")
	public String urlImage;
	@JsonProperty("totSeat")
	public Integer totSeat;
	@JsonProperty("price")
	public Integer price;

}
