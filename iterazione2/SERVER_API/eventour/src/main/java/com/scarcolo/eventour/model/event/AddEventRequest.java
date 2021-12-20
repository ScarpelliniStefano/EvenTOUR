package com.scarcolo.eventour.model.event;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


// TODO: Auto-generated Javadoc
/**
 * The Class AddEventRequest.
 */
public class AddEventRequest {
	
	/** The title. */
	@JsonProperty("title")
	public String title;
	
	/** The description. */
	@JsonProperty("description")
	public String description;
	
	/** The location. */
	@JsonProperty("location")
	public Location location;
	
	/** The types. */
	@JsonProperty("types")
	public String[] types;
	
	/** The data ora. */
	@JsonProperty("dataOra")
	public LocalDateTime dataOra;
	
	/** The manager id. */
	@JsonProperty("managerId")
	public String managerId;
	
	/** The url image. */
	@JsonProperty("urlImage")
	public String urlImage;
	
	/** The tot seat. */
	@JsonProperty("totSeat")
	public Integer totSeat;
	
	/** The price. */
	@JsonProperty("price")
	public Double price;

}
