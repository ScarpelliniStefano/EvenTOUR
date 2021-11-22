/**
 * 
 */
package com.scarcolo.eventour.model.booking;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author stefa
 *
 */
@Document(collection="bookings")
public class Booking {
	@Id
	private String id;
	private String userId;
	private String eventId;
	private Integer prenotedSeat;	
	
	public Booking(AddBookingRequest request) {
        this.setUserId(request.userId);
        this.setEventId(request.eventId);
        this.setPrenotedSeat(request.prenotedSeat);
    }
	
	public Booking() {
		
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
}
