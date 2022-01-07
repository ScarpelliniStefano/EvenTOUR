package com.scarcolo.eventour.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminReportResponse.
 */
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
	
	/** The number of events. */
	@JsonProperty("numEventi")
	private Integer numEventi;
	
	/** The number of events future. */
	@JsonProperty("numFuturi")
	private Integer numFuturi;
	
	/** The media of person comes to past event. */
	@JsonProperty("comesMean")
	private Double comesMean;
	
	/** The rating. */
	@JsonProperty("rating")
	private Double rating;
	
	
	/**
	 * Instantiates a new admin report response.
	 *
	 * @param id the id
	 * @param codicePIVA the codice PIVA
	 * @param managerPlusResponse the manager plus response
	 * @param numEventi the num eventi
	 * @param numFuturi the num futuri
	 * @param mediaComes the media comes
	 * @param rating the rating
	 */
	public AdminReportResponse(String id, String codicePIVA, ManagerPlusResponse managerPlusResponse,
			Integer numEventi, Integer numFuturi, Double mediaComes, Double rating) {
		this.id=id;
		this.codicePIVA=codicePIVA;
		this.manager=managerPlusResponse;
		this.numEventi=numEventi;
		this.comesMean = Math.round(mediaComes*100.0)/100.0;
		this.numFuturi=numFuturi;
		this.rating = Math.round(rating*100.0)/100.0;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * Gets the codice PIVA.
	 *
	 * @return the codice PIVA
	 */
	public String getCodicePIVA() {
		return codicePIVA;
	}


	/**
	 * Sets the codice PIVA.
	 *
	 * @param codicePIVA the new codice PIVA
	 */
	public void setCodicePIVA(String codicePIVA) {
		this.codicePIVA = codicePIVA;
	}


	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public ManagerPlusResponse getManager() {
		return manager;
	}


	/**
	 * Sets the manager.
	 *
	 * @param manager the new manager
	 */
	public void setManager(ManagerPlusResponse manager) {
		this.manager = manager;
	}


	/**
	 * Gets the num eventi.
	 *
	 * @return the num eventi
	 */
	public Integer getNumEventi() {
		return numEventi;
	}


	/**
	 * Sets the num eventi.
	 *
	 * @param numEventi the new num eventi
	 */
	public void setNumEventi(Integer numEventi) {
		this.numEventi = numEventi;
	}


	/**
	 * Gets the num futuri.
	 *
	 * @return the num futuri
	 */
	public Integer getNumFuturi() {
		return numFuturi;
	}


	/**
	 * Sets the num futuri.
	 *
	 * @param numFuturi the new num futuri
	 */
	public void setNumFuturi(Integer numFuturi) {
		this.numFuturi = numFuturi;
	}


	/**
	 * Gets the comes mean.
	 *
	 * @return the comes mean
	 */
	public Double getComesMean() {
		return comesMean;
	}


	/**
	 * Sets the comes mean.
	 *
	 * @param comesMean the new comes mean
	 */
	public void setComesMean(Double comesMean) {
		this.comesMean = Math.round(comesMean*100.0)/100.0;
	}


	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}


	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Double rating) {
		this.rating = Math.round(rating*100.0)/100.0;
	}
	
}
