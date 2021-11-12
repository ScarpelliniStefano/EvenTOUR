/**
 * 
 */
package com.scarcolo.eventour.model.sponsorship;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author stefa
 *
 */
@Document(collection = "sponsorship")
public class Sponsorship {
	
	@Id
	private String id;
	
	private String eventId;
	private String userId;
	
	/**
	 * 
	 * @param request
	 */
	public Sponsorship(AddSponsorshipRequest request) {
		setEventId(request.eventId);
		setUserId(request.userId);
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
		return eventId;
	}
	/**
	 * @param event the event to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return userId;
	}
	/**
	 * @param user the user to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
