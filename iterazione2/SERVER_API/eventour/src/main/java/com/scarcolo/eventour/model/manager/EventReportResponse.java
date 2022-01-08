package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.event.EventResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class EventReportResponse.
 */
public class EventReportResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String  id;
	
	/** The title. */
	@JsonProperty("title")
	private String title;
	
	/** The details of event. */
	@JsonProperty("eventDetails")
	private EventResponse event;
	
	/** The occuped seat. */
	@JsonProperty("occupedSeat")
	private Integer occupedSeat;
	
	/** The comed people. */
	@JsonProperty("comedPeople")
	private Integer comedPeople;
	
	/** The estimate entrance. */
	@JsonProperty("balance")
	private Double saldo;
	
	/** The estimate loss. */
	@JsonProperty("lost")
	private Double perdita;

	/**
	 * Instantiates a new event report response.
	 *
	 * @param id the id
	 * @param title the title
	 * @param event the event
	 * @param occupedSeat the occuped seat
	 * @param comedPeople the comed people
	 * @param saldo the saldo
	 * @param perdita the perdita
	 */
	public EventReportResponse(String id, String title, EventResponse event, Integer occupedSeat, Integer comedPeople,
			Double saldo, Double perdita) {
		super();
		this.id = id;
		this.title = title;
		this.event = event;
		this.occupedSeat = occupedSeat;
		this.comedPeople = comedPeople;
		this.saldo = Math.round(saldo*100.0)/100.0;
		this.perdita = Math.round(perdita*100.0)/100.0;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventResponse getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventResponse event) {
		this.event = event;
	}

	/**
	 * Gets the occuped seat.
	 *
	 * @return the occuped seat
	 */
	public Integer getOccupedSeat() {
		return occupedSeat;
	}

	/**
	 * Sets the occuped seat.
	 *
	 * @param occupedSeat the new occuped seat
	 */
	public void setOccupedSeat(Integer occupedSeat) {
		this.occupedSeat = occupedSeat;
	}

	/**
	 * Gets the comed people.
	 *
	 * @return the comed people
	 */
	public Integer getComedPeople() {
		return comedPeople;
	}

	/**
	 * Sets the comed people.
	 *
	 * @param comedPeople the new comed people
	 */
	public void setComedPeople(Integer comedPeople) {
		this.comedPeople = comedPeople;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the perdita.
	 *
	 * @return the perdita
	 */
	public Double getPerdita() {
		return perdita;
	}

	/**
	 * Sets the perdita.
	 *
	 * @param perdita the new perdita
	 */
	public void setPerdita(Double perdita) {
		this.perdita = perdita;
	}
}