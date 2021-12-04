/**
 * 
 */
package com.scarcolo.eventour.model.ticketinsp;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;





// TODO: Auto-generated Javadoc
/**
 * The Class TicketInspResponse.
 *
 * @author stefa
 */

public class TicketInspResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String  id;
	
	/** The code. */
	@JsonProperty("code")
	private String code;
	
	/** The event id. */
	@JsonProperty("eventId")
	private String eventId;
	
	/** The full name. */
	@JsonProperty("fullName")
	private String fullName;
	
	/** The password. */
	@JsonProperty("password")
	private String password;
	
	/**
	 * Instantiates a new ticket insp response.
	 *
	 * @param ticket the ticket
	 */
	public TicketInspResponse(TicketInsp ticket){
		this.id=ticket.getId();
		this.setCode(ticket.getCode());
        this.setEventId(ticket.getEventId());
        this.setFullName(ticket.getFullName());
        this.setPassword(ticket.getPassword());
    }
	
	 /**
 	 * Instantiates a new ticket insp response.
 	 */
 	public TicketInspResponse() {
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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = Functionalities.getMd5(password);
	}

	
	
	
}
