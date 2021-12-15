/**
 * 
 */
package com.scarcolo.eventour.model.booking;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


// TODO: Auto-generated Javadoc
/**
 * The Class Booking.
 *
 * @author stefa
 */
@Document(collection="bookings")
public class Booking {
	
	/** The id. */
	@Id
	private String id;
	
	/** The user id. */
	private ObjectId userId;
	
	/** The event id. */
	private ObjectId eventId;
	
	/** The prenoted seat. */
	private Integer prenotedSeat;	
	
	/** Come? */
	private Boolean come;	
	
	private int rating;
	
	/**
	 * Instantiates a new booking.
	 *
	 * @param request the request
	 */
	public Booking(AddBookingRequest request) {
        this.setUserId(new ObjectId(request.userId));
        this.setEventId(new ObjectId(request.eventId));
        this.setPrenotedSeat(request.prenotedSeat);
        this.setCome(false);
        this.setRating(-1);
    }
	
	/**
	 * Instantiates a new booking.
	 */
	public Booking() {
		
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
		return userId.toHexString();
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId.toHexString();
	}

	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(ObjectId eventId) {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
