package com.scarcolo.eventour.model.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;

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
	private LocalDate dateOfBirth;
	
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
	
	/** The activation. */
	@JsonProperty("active")
	private boolean active;
	
	/** The date of renewal. */
	@JsonProperty("dateRenewal")
	private LocalDate dateRenewal;
	
	/** The bookings. */
	@JsonProperty("event")
	private Event[] event;

	public ReportAdmResponse(String id, String name, String surname, LocalDate dateOfBirth, Location residence,
			String codicePIVA, String ragioneSociale, String mail, boolean active, LocalDate dateRenewal,
			Event[] event) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.residence = residence;
		this.codicePIVA = codicePIVA;
		this.ragioneSociale = ragioneSociale;
		this.mail = mail;
		this.active = active;
		this.dateRenewal = dateRenewal;
		this.event = event;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getDateRenewal() {
		return dateRenewal;
	}

	public void setDateRenewal(LocalDate dateRenewal) {
		this.dateRenewal = dateRenewal;
	}

	public Event[] getEvent() {
		return event;
	}

	public void setEvent(Event[] event) {
		this.event = event;
	}

	
}
