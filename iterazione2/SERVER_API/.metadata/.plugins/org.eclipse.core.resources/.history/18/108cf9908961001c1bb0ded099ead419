/**
 * 
 */
package com.scarcolo.eventour.model.request;


import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class Request.
 *
 * @author stefa
 */
@Document(collection = "requests")
public class Request{
	
	/** The id. */
	@Id
	private String id;
	
	/** The activation. */
	@JsonProperty("managerId")
	private ObjectId managerId;
	
	/** The activation. */
	@JsonProperty("active")
	private boolean active;
	
	/** The date of renewal. */
	@JsonProperty("dateRenewal")
	private Date dateRenewal;

	/**
	 * Instantiates a new manager.
	 *
	 * @param request the request
	 * @throws Exception the exception
	 */
	public Request(String idMan) throws Exception{
		this.setManagerId(new ObjectId(idMan));
		this.setActive(false);
		this.setDateRenewal(null);
	}
	
	/**
	 * Instantiates a new manager.
	 */
	public Request() {
		super();
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

	public ObjectId getManagerId() {
		return managerId;
	}

	public void setManagerId(ObjectId managerId) {
		this.managerId = managerId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateRenewal() {
		return dateRenewal;
	}

	public void setDateRenewal(Date dateRenewal) {
		this.dateRenewal = dateRenewal;
	}


	
}
