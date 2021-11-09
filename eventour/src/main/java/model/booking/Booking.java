/**
 * 
 */
package model.booking;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import model.event.Event;
import model.user.User;

/**
 * @author stefa
 *
 */
@Document(collection = "bookings")
public class Booking {
	@Id
	private Integer id;
	private User user;
	private Event event;
	private Integer prenotedSeat;	
	
	/**
	 * @param id
	 * @param user
	 * @param event
	 * @param prenotedSeat
	 */
	public Booking(Integer id, User user, Event event, Integer prenotedSeat) {
		super();
		setId(id);
		setUser(user);
		setEvent(event);
		setPrenotedSeat(prenotedSeat);
	}
	/**
	 * @return the id
	 */
	protected Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	protected void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	protected User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	protected void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the event
	 */
	protected Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	protected void setEvent(Event event) {
		this.event = event;
	}
	/**
	 * @return the prenotedSeat
	 */
	protected Integer getPrenotedSeat() {
		return prenotedSeat;
	}
	/**
	 * @param prenotedSeat the prenotedSeat to set
	 */
	protected void setPrenotedSeat(Integer prenotedSeat) {
		this.prenotedSeat = prenotedSeat;
	}
}