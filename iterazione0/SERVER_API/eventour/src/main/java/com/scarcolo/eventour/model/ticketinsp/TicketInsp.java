/**
 * 
 */
package com.scarcolo.eventour.model.ticketinsp;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;

// TODO: Auto-generated Javadoc
/**
 * The Class TicketInsp.
 *
 * @author stefa
 */
@Document(collection = "ticketInspectors")
public class TicketInsp{
	
	/** The id. */
	@Id
	private String id;
	
	/** The code. */
	private String code;
	
	/** The password. */
	private String password;
	
	/** The full name. */
	private String fullName;
	
	/** The event id. */
	private ObjectId eventId;
	
	/**
	 * Instantiates a new ticket insp.
	 *
	 * @param request the request of new ticket inspector
	 * @throws Exception the exception of wrong code
	 */
	public TicketInsp(AddTicketInspRequest request) throws Exception {
        this.setPassword(request.password);
        this.setCode(request.code);
        this.setEventId(request.eventId);
        this.setFullName(request.fullName);
    }
	
	/**
	 * Instantiates a new ticket insp.
	 */
	public TicketInsp() {
		super();
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
		this.password = password;
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
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId.toHexString();
	}

	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(String eventId) {
		this.eventId = new ObjectId(eventId);
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 * @throws Exception the exception
	 */
	public void setCode(String code) throws Exception {
		boolean res=Functionalities.isValidCode(code);
		if(res) {
			this.code=code;
		}else {
			throw new Exception();
		}
			
	}
}
