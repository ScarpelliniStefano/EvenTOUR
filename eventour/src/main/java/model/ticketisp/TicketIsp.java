/**
 * 
 */
package model.ticketisp;

import functions.Functionalities;
import model.Account;
import model.event.Event;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author stefa
 *
 */
@Document(collection = "ticketInspectors")
public class TicketIsp extends Account{
	private String fullName;
	private Event event;
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param fullName
	 * @param event
	 * @throws Exception 
	 */
	public TicketIsp(Integer id, String code, String password, String fullName, Event event) throws Exception {
		super(id, password);
		setCode(code);
		setFullName(fullName);
		setEvent(event);
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public String getCode() {
		return getUsername();
	}
	
	/**
	 * @param username the username to set
	 * @throws Exception 
	 */
	public void setCode(String code) throws Exception {
		boolean res=Functionalities.isValidCode(code);
		if(res) {
			setUsername(code);
		}else {
			throw new Exception();
		}
			
	}
}
