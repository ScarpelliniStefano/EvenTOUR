/**
 * 
 */
package com.scarcolo.eventour.model.ticketinsp;



import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;





/**
 * @author stefa
 *
 */

public class TicketInspResponse {
	
	@JsonProperty("id")
	private String  id;
	@JsonProperty("code")
	private String code;
	@JsonProperty("eventId")
	private String eventId;
	@JsonProperty("fullName")
	private String fullName;
	@JsonProperty("password")
	private String password;
	
	public TicketInspResponse(TicketInsp ticket){
		this.id=ticket.getId();
		this.setCode(ticket.getCode());
        this.setEventId(ticket.getEventId());
        this.setFullName(ticket.getFullName());
        this.setPassword(ticket.getPassword());
    }
	
	 public TicketInspResponse() {
	    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
