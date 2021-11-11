package com.scarcolo.eventour.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AddEventRequest {
	
	@JsonProperty("title")
	public String title;
	@JsonProperty("description")
	public String description;
	@JsonProperty("location")
	public String location;
	@JsonProperty("types")
	public Object[] types;
	@JsonProperty("dataAAAA")
	public String dataAAAA;
	@JsonProperty("dataMM")
	public String dataMM;
	@JsonProperty("dataGG")
	public String dataGG;
	@JsonProperty("time")
	public String time;
	@JsonProperty("managerId")
	public String managerId;
	@JsonProperty("urlImage")
	public String urlImage;
	@JsonProperty("totSeat")
	public Integer totSeat;

}
