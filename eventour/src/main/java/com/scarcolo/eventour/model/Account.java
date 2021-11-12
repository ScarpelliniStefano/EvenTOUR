package com.scarcolo.eventour.model;

import org.springframework.data.annotation.Id;


public abstract class Account{
	@Id
	private Integer id;
	private String username;
	private String password;

	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public Account(Integer id, String username, String password) {
		super();
		setId(id);
		setUsername(username);
		setPassword(password);
	}

	public Account(Integer id, String password) {
		setId(id);
		setPassword(password);
	}
	
	public Account(String username, String password) {
		setUsername(username);
		setPassword(password);
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	
}
