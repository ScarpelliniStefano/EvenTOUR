/**
 * 
 */
package com.scarcolo.eventour.model.admin;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;


// TODO: Auto-generated Javadoc
/**
 * The Class ManagerResponse.
 *
 * @author stefa
 */

public class AdminResponse{
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The surname. */
	@JsonProperty("surname")
	private String surname;
	
	/** The mail. */
	@JsonProperty("mail")
	private String mail;
	
	/** The password. */
	@JsonProperty("password")
	private String password;
	

	/**
	 * Instantiates a new manager response.
	 *
	 * @param manager the manager
	 */
	public AdminResponse(Admin admin){
		this.setId(admin.getId());
		this.setMail(admin.getMail());
		this.setPassword(admin.getPassword());
		this.setName(admin.getName());
		this.setSurname(admin.getSurname());
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

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = Functionalities.getMd5(password);
	}
	
	
}