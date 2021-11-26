package com.scarcolo.eventour.model.manager;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;

public class TicketManResponse {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("mail")
	private String mail;
	@JsonProperty("events")
	private EventTick events;
	public TicketManResponse(String id, String name, String surname, String mail, EventTick events) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.events = events;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public EventTick getEvents() {
		return events;
	}
	public void setEvents(EventTick events) {
		this.events = events;
	}
	

	
}
