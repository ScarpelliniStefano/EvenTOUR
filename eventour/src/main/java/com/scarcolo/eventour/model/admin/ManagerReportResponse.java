package com.scarcolo.eventour.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;

public class ManagerReportResponse {

	/** The id. */
	@JsonProperty("id")
	private String  id;
	
	/** The codice PIVA. */
	@JsonProperty("codicePIVA")
	private String codicePIVA;
	
	/** The managers details. */
	@JsonProperty("managerDetails")
	private ManagerPlusResponse manager;
	
	/** The num events. */
	@JsonProperty("numEventi")
	private Integer numEventi;
	
	/** The media of occupation. */
	@JsonProperty("OccupationMean")
	private Double occupationMean;
	
	/** The rating. */
	@JsonProperty("rating")
	private Double rating;
	
	
	public ManagerReportResponse(String id, String codicePIVA, ManagerPlusResponse managerPlusResponse,
			Integer numEventi, Double mediaOccuped, Double rating) {
		this.setId(id);
		this.setCodicePIVA(codicePIVA);
		this.setManager(managerPlusResponse);
		this.setNumEventi(numEventi);
		this.setOccupationMean(mediaOccuped);
		this.setRating(rating);
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCodicePIVA() {
		return codicePIVA;
	}


	public void setCodicePIVA(String codicePIVA) {
		this.codicePIVA = codicePIVA;
	}


	public ManagerPlusResponse getManager() {
		return manager;
	}


	public void setManager(ManagerPlusResponse manager) {
		this.manager = manager;
	}


	public Integer getNumEventi() {
		return numEventi;
	}


	public void setNumEventi(Integer numEventi) {
		this.numEventi = numEventi;
	}


	public Double getOccupationMean() {
		return occupationMean;
	}


	public void setOccupationMean(Double occupationMean) {
		this.occupationMean = occupationMean;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}
	
}
