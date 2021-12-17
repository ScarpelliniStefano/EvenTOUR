package com.scarcolo.eventour.model.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventPlus;
import com.scarcolo.eventour.model.request.Request;

// TODO: Auto-generated Javadoc
/**
 * The Class TicketManResponse.
 */
public class ReportAdmResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The surname. */
	@JsonProperty("surname")
	private String surname;
	
	/** The date of birth. */
	@JsonProperty("dateOfBirth")
	private Date dateOfBirth;
	
	/** The residence. */
	@JsonProperty("residence")
	private Location residence;
	
	/** The codice PIVA. */
	@JsonProperty("codicePIVA")
	private String codicePIVA;
	
	/** The ragione sociale. */
	@JsonProperty("ragioneSociale")
	private String ragioneSociale;
	
	/** The mail. */
	@JsonProperty("mail")
	private String mail;
	
	/** The bookings. */
	@JsonProperty("events")
	private EventPlus[] events;
	
	/** The bookings. */
	@JsonProperty("request")
	private Request[] request;

	public ReportAdmResponse(String id, String name, String surname, Date dateOfBirth, Location residence,
			String codicePIVA, String ragioneSociale, String mail, EventPlus[] events, Request[] request) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.residence = residence;
		this.codicePIVA = codicePIVA;
		this.ragioneSociale = ragioneSociale;
		this.mail = mail;
		this.events = events;
		this.request = request;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Location getResidence() {
		return residence;
	}

	public void setResidence(Location residence) {
		this.residence = residence;
	}

	public String getCodicePIVA() {
		return codicePIVA;
	}

	public void setCodicePIVA(String codicePIVA) {
		this.codicePIVA = codicePIVA;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public EventPlus[] getEvent() {
		return events;
	}

	public void setEvent(EventPlus[] event) {
		this.events = event;
	}

	public Request[] getRequest() {
		return request;
	}

	public void setRequest(Request[] request) {
		this.request = request;
	}

	
}