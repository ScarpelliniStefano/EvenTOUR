/**
 * 
 */
package com.scarcolo.eventour.model.ticketinsp;

import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Account;

/**
 * @author stefa
 *
 */
@Document(collection = "ticketInspectors")
public class TicketInsp extends Account{
	private String fullName;
	private String eventId;
	
	public TicketInsp(AddTicketInspRequest request) throws Exception {
        super(false,request.code,request.password);
        setCode(request.code);
        this.eventId=request.eventId;
        this.fullName=request.fullName;
    }
	
	public TicketInsp() {
		super();
	}
	
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getCode() {
		return super.getUsername();
	}

	public void setCode(String code) throws Exception {
		boolean res=Functionalities.isValidCode(code);
		if(res) {
			setUsername(code);
		}else {
			throw new Exception();
		}
			
	}
}
