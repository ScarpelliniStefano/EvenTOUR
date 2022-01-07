package com.scarcolo.eventour.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountResponse.
 */
public class AccountResponse {
	
	/** The type user. */
	@JsonProperty("typeUser")
	private String typeUser;
	
	/** The user. */
	@JsonProperty("user")
	private Object user;
	
	
	/**
	 * Instantiates a new account response.
	 *
	 * @param typeUser the type user
	 * @param user the user
	 */
	public AccountResponse(String typeUser, Object user) {
		this.typeUser = typeUser;
		this.user = user;
	}
	
	/**
	 * Gets the type user.
	 *
	 * @return the type user
	 */
	public String getTypeUser() {
		return typeUser;
	}
	
	/**
	 * Sets the type user.
	 *
	 * @param typeUser the new type user
	 */
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Object getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Object user) {
		this.user = user;
	}
	
}
