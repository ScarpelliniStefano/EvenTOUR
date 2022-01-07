/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.request.Request;


// TODO: Auto-generated Javadoc
/**
 * The Class ManagerPlusResponse.
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
	 * @param req the req
	 */
	public ManagerPlusResponse(Manager manager, Request req){
		super(manager);
		this.active=req.isActive();
		this.dateRenewal=req.getDateRenewal();
	}


	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}


	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


	/**
	 * Gets the date renewal.
	 *
	 * @return the date renewal
	 */
	public Date getDateRenewal() {
		return dateRenewal;
	}


	/**
	 * Sets the date renewal.
	 *
	 * @param dateRenewal the new date renewal
	 */
	public void setDateRenewal(Date dateRenewal) {
		this.dateRenewal = dateRenewal;
	}

	
	
	
}
