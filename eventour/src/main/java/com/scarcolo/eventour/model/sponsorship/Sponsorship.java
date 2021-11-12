/**
 * 
 */
package com.scarcolo.eventour.model.sponsorship;

/**
 * @author stefa
 *
 */
public class Sponsorship {
	private Integer id;
	private Event event;
	private User user;
	/**
	 * @param id
	 * @param event
	 * @param user
	 */
	public Sponsorship(Integer id, Event event, User user) {
		super();
		setId(id);
		setEvent(event);
		setUser(user);
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
}
