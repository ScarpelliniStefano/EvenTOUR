/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.user.UserResponse;


// TODO: Auto-generated Javadoc
/**
 * The Class EventResponseTourCompleted.
 *
 * @author stefa
 */

public class EventResponseTourComplete{
	
	/** The number of event requested. */
	@JsonProperty("numberRequested")
	public Integer numberRequested;
	
	/** The number of event finded. */
	@JsonProperty("numberFinded")
	public Integer numberFinded;
	
	/** The total price of all tour. */
	@JsonProperty("totalPrice")
	public Double totalPrice;
	
	/** The total air distance. */
	@JsonProperty("totalAirDistance")
	public Double totalDistance;
	
	/** The user. */
	@JsonProperty("user")
	public UserResponse user;
	
	/** The events */
	@JsonProperty("events")
	public List<EventResponseTour> events;
	
	/**
	 * Instantiates a new event response tour complete.
	 *
	 * @param event the events
	 * @param u the user
	 * @param req the requested number of events
	 * @param finded the finded number of events
	 * @param price the total price
	 * @param distance the total distance
	 */
	public EventResponseTourComplete(List<EventResponseTour> event,UserResponse u, int req, int finded, double price, double distance){
		this.events=event;
		this.numberFinded=finded;
		this.numberRequested=req;
		this.totalDistance=distance;
		this.totalPrice=Math.round(price*100.0)/100.0;
		this.user=u;
    }
	
	 /**
 	 * Instantiates a new event response tour complete.
 	 */
 	public EventResponseTourComplete() {
	    }

	
	
	
}
