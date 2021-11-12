/**
 * 
 */
package com.scarcolo.eventour.model.ticketisp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Account;

/**
 * @author stefa
 *
 */
@Document(collection = "ticketisp")
public class TicketIsp extends Account{
	
	@Id
	private String id;
	
	private String code;
	private String eventId;
	private String password;
	private String fullName;
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param fullName
	 * @param event
	 * @throws Exception 
	 */
	public TicketIsp(AddTicketIspRequest request) throws Exception {
		super(request.code, request.password);
		setEventId(request.eventId);
		setPassword(request.password);
		setFullName(request.fullName);
	}


	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}


	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
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
