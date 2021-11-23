/**
 * 
 */
package com.scarcolo.eventour.model.booking;

import org.bson.types.ObjectId;
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
	private ObjectId userId;
	private ObjectId eventId;
	private Integer prenotedSeat;	
	
	public Booking(AddBookingRequest request) {
        this.setUserId(new ObjectId(request.userId));
        this.setEventId(new ObjectId(request.eventId));
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
		return userId.toHexString();
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public String getEventId() {
		return eventId.toHexString();
	}

	public void setEventId(ObjectId eventId) {
		this.eventId = eventId;
	}

	public Integer getPrenotedSeat() {
		return prenotedSeat;
	}

	public void setPrenotedSeat(Integer prenotedSeat) {
		this.prenotedSeat = prenotedSeat;
	}
}
