/**
 * 
 */
package com.scarcolo.eventour.model.sponsorship;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author stefa
 *
 */
@Document(collection = "sponsor")
public class Sponsorship {
	
	@Id
	private String id;
	
	private ObjectId eventId;
	private ObjectId userId;
	
	/**
	 * 
	 * @param request
	 */
	public Sponsorship(AddSponsorshipRequest request) {
		this.setEventId(request.eventId);
		this.setUserId(request.userId);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the event
	 */
	public String getEventId() {
		return eventId.toHexString();
	}
	/**
	 * @param event the event to set
	 */
	public void setEventId(String eventId) {
		this.eventId = new ObjectId(eventId);
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return userId.toHexString();
	}
	/**
	 * @param user the user to set
	 */
	public void setUserId(String userId) {
		this.userId = new ObjectId(userId);
	}
}
