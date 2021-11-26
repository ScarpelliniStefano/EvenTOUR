package com.scarcolo.eventour.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserBookedResponse {
	@JsonProperty("id")
	private String id;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("eventId")
	private String eventId;
	@JsonProperty("prenotedSeat")
	private Integer prenotedSeat;
	@JsonProperty("come")
	private Boolean come;
	@JsonProperty("user")
	private User[] user;
	
	public UserBookedResponse(String id, String userId, String eventId, Integer prenotedSeat,Boolean come, User[] user) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.prenotedSeat = prenotedSeat;
		this.come = come;
		this.setUser(user);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Integer getPrenotedSeat() {
		return prenotedSeat;
	}
	public void setPrenotedSeat(Integer prenotedSeat) {
		this.prenotedSeat = prenotedSeat;
	}
	public Boolean getCome() {
		return come;
	}
	public void setCome(Boolean come) {
		this.come = come;
	}
	public User[] getUser() {
		return user;
	}
	public void setUser(User[] user) {
		this.user = user;
	}
	
}
