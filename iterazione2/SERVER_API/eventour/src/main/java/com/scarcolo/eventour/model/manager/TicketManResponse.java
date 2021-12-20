package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class TicketManResponse.
 */
public class TicketManResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The surname. */
	@JsonProperty("surname")
	private String surname;
	
	/** The mail. */
	@JsonProperty("mail")
	private String mail;
	
	/** The ticket inspectors. */
	@JsonProperty("tickets")
	private EventTick tickets;
	
	/**
	 * Instantiates a new ticket man response.
	 *
	 * @param id the id
	 * @param name the name
	 * @param surname the surname
	 * @param mail the mail
	 * @param tickets the tickets associated
	 */
	public TicketManResponse(String id, String name, String surname, String mail, EventTick tickets) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.tickets = tickets;
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
	 * Gets the event tickets.
	 *
	 * @return the ticket inspectors
	 */
	public EventTick getTickets() {
		return tickets;
	}
	
	/**
	 * Sets the events.
	 *
	 * @param tickets the ticket inspectors associated
	 */
	public void setTickets(EventTick tickets) {
		this.tickets = tickets;
	}
	

	
}
