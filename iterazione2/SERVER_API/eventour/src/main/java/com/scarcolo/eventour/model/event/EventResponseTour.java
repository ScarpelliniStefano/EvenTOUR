/**
 * 
 */
package com.scarcolo.eventour.model.event;



import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class EventResponseTour.
 *
 * @author stefa
 */

public class EventResponseTour extends EventResponse{
	
	/** The distance from start. */
	@JsonProperty("distanceFromStart")
	private Double distance;
	
	/** The price tot for all people in the single event. */
	@JsonProperty("priceTotPersone")
	private Double priceTot;
	
	/**
	 * Instantiates a new event response tour.
	 *
	 * @param event the event
	 */
	public EventResponseTour(Event event){
		this.setId(event.getId());
		this.setTitle(event.getTitle());
        this.setDescription(event.getDescription());
        this.setLocation(event.getLocation());
        this.setTypes(event.getTypes());
        this.setDataOra(event.getDataOra());
        this.setManagerId(event.getManagerId());
        this.setUrlImage(event.getUrlImage());
        this.setTotSeat(event.getTotSeat());
        this.setFreeSeat(event.getFreeSeat());
        this.setPrice(event.getPrice());
    }
	
	 /**
 	 * Instantiates a new event response tour.
 	 */
 	public EventResponseTour() {
	    }

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * Gets the price tot.
	 *
	 * @return the price tot
	 */
	public Double getPriceTot() {
		return priceTot;
	}

	/**
	 * Sets the price tot.
	 *
	 * @param priceTot the new price tot
	 */
	public void setPriceTot(Double priceTot) {
		this.priceTot = Math.round(priceTot*100.0)/100.0;
	}

	
	
	
}
