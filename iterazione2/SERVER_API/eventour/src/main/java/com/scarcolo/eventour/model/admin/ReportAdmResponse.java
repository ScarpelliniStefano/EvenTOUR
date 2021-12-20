package com.scarcolo.eventour.model.admin;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.event.EventPlus;
import com.scarcolo.eventour.model.request.Request;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportAdmResponse.
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
	
	/** The events. */
	@JsonProperty("events")
	private EventPlus[] events;
	
	/** The request data. */
	@JsonProperty("request")
	private Request[] request;

	/**
	 * Instantiates a new report admin response.
	 *
	 * @param id the id
	 * @param name the name
	 * @param surname the surname
	 * @param dateOfBirth the date of birth
	 * @param residence the residence
	 * @param codicePIVA the codice PIVA
	 * @param ragioneSociale the ragione sociale
	 * @param mail the mail
	 * @param events the events
	 * @param request the request
	 */
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the residence.
	 *
	 * @return the residence
	 */
	public Location getResidence() {
		return residence;
	}

	/**
	 * Sets the residence.
	 *
	 * @param residence the new residence
	 */
	public void setResidence(Location residence) {
		this.residence = residence;
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
	 * Gets the ragione sociale.
	 *
	 * @return the ragione sociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	/**
	 * Sets the ragione sociale.
	 *
	 * @param ragioneSociale the new ragione sociale
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventPlus[] getEvent() {
		return events;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventPlus[] event) {
		this.events = event;
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public Request[] getRequest() {
		return request;
	}

	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	public void setRequest(Request[] request) {
		this.request = request;
	}

	
}
