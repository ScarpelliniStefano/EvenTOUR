/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.request.Request;


// TODO: Auto-generated Javadoc
/**
 * The Class ManagerResponse.
 *
 * @author stefa
 */

public class ManagerPlusResponse extends ManagerResponse{
	
	/** The activation. */
	@JsonProperty("active")
	private boolean active;
	
	/** The date of renewal. */
	@JsonProperty("dateRenewal")
	private Date dateRenewal;
	

	/**
	 * Instantiates a new request response.
	 *
	 * @param manager the manager
	 * @param request the request 
	 */
	public ManagerPlusResponse(Manager manager, Request req){
		super(manager);
		this.setActive(req.isActive());
		this.setDateRenewal(req.getDateRenewal());
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
