package com.scarcolo.eventour.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class UserBookedResponse.
 */
public class UserBookedResponse {
	
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
	
	/** The user. */
	@JsonProperty("user")
	private User[] user;
	
	/**
	 * Instantiates a new user booked response.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param eventId the event id
	 * @param prenotedSeat the prenoted seat
	 * @param come the come
	 * @param user the user
	 */
	public UserBookedResponse(String id, String userId, String eventId, Integer prenotedSeat,Boolean come, User[] user) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.prenotedSeat = prenotedSeat;
		this.come = come;
		this.setUser(user);
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
