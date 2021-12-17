package com.scarcolo.eventour.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class EventBookedResponse.
 */
public class EventBookedResponse {
	
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
	
	/** Come? */
	@JsonProperty("come")
	private Boolean come;
	
	/** The event. */
	@JsonProperty("event")
	private Event[] event;
	
	/** Rating */
	@JsonProperty("review")
	private Integer review;
	
	/**
	 * Instantiates a new event booked response.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param eventId the event id
	 * @param prenotedSeat the prenoted seat
	 * @param come the come
	 * @param event the event
	 */
	public EventBookedResponse(String id, String userId, String eventId, Integer prenotedSeat,Boolean come, Event[] event, Integer review) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.prenotedSeat = prenotedSeat;
		this.come = come;
		this.event=event;
		this.review = review;
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
	 * Gets if the user come or not.
	 *
	 * @return the come
	 */
	public Boolean getCome() {
		return come;
	}
	
	/**
	 * Sets come.
	 *
	 * @param come the new come
	 */
	public void setCome(Boolean come) {
		this.come = come;
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
		this.event=event;
	}

	/**
	 * @return the review
	 */
	protected Integer getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	protected void setReview(Integer review) {
		this.review = review;
	}
	
}
