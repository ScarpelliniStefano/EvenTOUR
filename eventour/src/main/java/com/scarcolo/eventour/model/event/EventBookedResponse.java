package com.scarcolo.eventour.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;

public class EventBookedResponse {
	@JsonProperty("id")
	private String id;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("eventId")
	private String eventId;
	@JsonProperty("prenotedSeat")
	private Integer prenotedSeat;
	@JsonProperty("event")
	private Event[] event;
	
	public EventBookedResponse(String id, String userId, String eventId, Integer prenotedSeat, Event[] event) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.prenotedSeat = prenotedSeat;
		this.event = event;
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
	public Event[] getEvent() {
		return event;
	}
	public void setEvent(Event[] event) {
		this.event = event;
	}
	
}
