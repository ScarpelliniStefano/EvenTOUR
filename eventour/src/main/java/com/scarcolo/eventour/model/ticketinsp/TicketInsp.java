/**
 * 
 */
package com.scarcolo.eventour.model.ticketinsp;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;

/**
 * @author stefa
 *
 */
@Document(collection = "ticketInspectors")
public class TicketInsp{
	@Id
	private String id;
	private String code;
	private String password;
	private String fullName;
	private ObjectId eventId;
	public TicketInsp(AddTicketInspRequest request) throws Exception {
        this.setPassword(request.password);
        this.setCode(request.code);
        this.setEventId(request.eventId);
        this.setFullName(request.fullName);
    }
	
	public TicketInsp() {
		super();
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEventId() {
		return eventId.toHexString();
	}

	public void setEventId(String eventId) {
		this.eventId = new ObjectId(eventId);
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) throws Exception {
		boolean res=Functionalities.isValidCode(code);
		if(res) {
			this.code=code;
		}else {
			throw new Exception();
		}
			
	}
}
