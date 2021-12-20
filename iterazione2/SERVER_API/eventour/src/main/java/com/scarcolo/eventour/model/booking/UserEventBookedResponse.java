package com.scarcolo.eventour.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEventBookedResponse.
 */
public class UserEventBookedResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The user id. */
	@JsonProperty("userId")
	private String userId;
	
	/** The event id. */
	@JsonProperty("eventId")
	private String eventId;
	
	/** The prenoted seat. */
	@JsonProperty("prenotedSeat")
	private Integer prenotedSeat;
	
	/** The come. */
	@JsonProperty("come")
	private Boolean come;
	
	/** The review. */
	@JsonProperty("review")
	private Integer review;
	
	/** The user. */
	@JsonProperty("user")
	private User[] user;
	
	/** The event. */
	@JsonProperty("event")
	private Event[] event;
	
	/**
	 * Instantiates a new user event booked response.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param eventId the event id
	 * @param prenotedSeat the prenoted seat
	 * @param come the come
	 * @param review the review
	 * @param user the user
	 * @param event the event
	 */
	public UserEventBookedResponse(String id, String userId, String eventId, Integer prenotedSeat,Boolean come, int review, User[] user,Event[] event ) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.prenotedSeat = prenotedSeat;
		this.come = come;
		this.review = review;
		this.user = user;
		this.event = event;
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * Gets the prenoted seat.
	 *
	 * @return the prenoted seat
	 */
	public Integer getPrenotedSeat() {
		return prenotedSeat;
	}
	
	/**
	 * Sets the prenoted seat.
	 *
	 * @param prenotedSeat the new prenoted seat
	 */
	public void setPrenotedSeat(Integer prenotedSeat) {
		this.prenotedSeat = prenotedSeat;
	}
	
	/**
	 * Gets the come.
	 *
	 * @return the come
	 */
	public Boolean getCome() {
		return come;
	}
	
	/**
	 * Sets the come.
	 *
	 * @param come the new come
	 */
	public void setCome(Boolean come) {
		this.come = come;
	}
	
	/**
	 * Gets the review.
	 *
	 * @return the review
	 */
	public int getReview() {
		return review;
	}

	/**
	 * Sets the review.
	 *
	 * @param review the new review
	 */
	public void setReview(int review) {
		this.review = (review>0&&review<6) ? review : -1;
	}
	
	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event[] getEvent() {
		return event;
	}
	
	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(Event[] event) {
		this.event = event;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User[] getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User[] user) {
		this.user = user;
	}
	
}