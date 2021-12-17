package com.scarcolo.eventour.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;

public class AdminReportResponse {

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
	
	/** The num events. */
	@JsonProperty("numFuturi")
	private Integer numFuturi;
	
	/** The media of person comes. */
	@JsonProperty("comesMean")
	private Double comesMean;
	
	/** The rating. */
	@JsonProperty("rating")
	private Double rating;
	
	
	public AdminReportResponse(String id, String codicePIVA, ManagerPlusResponse managerPlusResponse,
			Integer numEventi, Integer numFuturi, Double mediaComes, Double rating) {
		this.setId(id);
		this.setCodicePIVA(codicePIVA);
		this.setManager(managerPlusResponse);
		this.setNumEventi(numEventi);
		this.setComesMean(mediaComes);
		this.setNumFuturi(numFuturi);
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


	public Integer getNumFuturi() {
		return numFuturi;
	}


	public void setNumFuturi(Integer numFuturi) {
		this.numFuturi = numFuturi;
	}


	public Double getComesMean() {
		return comesMean;
	}


	public void setComesMean(Double comesMean) {
		this.comesMean = Math.round(comesMean*100.0)/100.0;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = Math.round(rating*100.0)/100.0;
	}
	
}