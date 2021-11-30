/**
 * 
 */
package com.scarcolo.eventour.model.sponsorship;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Sponsorship.
 *
 * @author stefa
 */
@Document(collection = "sponsor")
public class Sponsorship {
	
	/** The id. */
	@Id
	private String id;
	
	/** The event id. */
	private ObjectId eventId;
	
	/** The user id. */
	private ObjectId userId;
	
	/**
	 * Instantiates a new sponsorship.
	 *
	 * @param request the request
	 */
	public Sponsorship(AddSponsorshipRequest request) {
		this.setEventId(request.eventId);
		this.setUserId(request.userId);
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
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the event id.
	 *
	 * @return the event
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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return userId.toHexString();
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = new ObjectId(userId);
	}
}
