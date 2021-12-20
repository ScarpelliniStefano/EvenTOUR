/**
 * 
 */
package com.scarcolo.eventour.model.event;



import com.fasterxml.jackson.annotation.JsonProperty;





// TODO: Auto-generated Javadoc
/**
 * The Class EventResponse.
 *
 * @author stefa
 */

public class EventResponseTour extends EventResponse{
	
	/** The distance. */
	@JsonProperty("distanceFromStart")
	private Double distance;
	
	/** The distance. */
	@JsonProperty("priceTotPersone")
	private Double priceTot;
	
	/**
	 * Instantiates a new event response.
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
 	 * Instantiates a new event response.
 	 */
 	public EventResponseTour() {
	    }

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getPriceTot() {
		return priceTot;
	}

	public void setPriceTot(Double priceTot) {
		this.priceTot = Math.round(priceTot*100.0)/100.0;
	}

	
	
	
}