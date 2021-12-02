package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.event.EventResponse;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EventResponse getEvent() {
		return event;
	}

	public void setEvent(EventResponse event) {
		this.event = event;
	}

	public Integer getOccupedSeat() {
		return occupedSeat;
	}

	public void setOccupedSeat(Integer occupedSeat) {
		this.occupedSeat = occupedSeat;
	}

	public Integer getComedPeople() {
		return comedPeople;
	}

	public void setComedPeople(Integer comedPeople) {
		this.comedPeople = comedPeople;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getPerdita() {
		return perdita;
	}

	public void setPerdita(Double perdita) {
		this.perdita = perdita;
	}
}
