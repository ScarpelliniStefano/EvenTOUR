/**
 * 
 */
package com.scarcolo.eventour.model.admin;


import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminResponse.
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
	
	/** The role. */
	@JsonProperty("role")
	private String role;
	

	/**
	 * Instantiates a new admin response.
	 *
	 * @param admin the admin
	 */
	public AdminResponse(Admin admin){
		this.id=admin.getId();
		this.mail=admin.getMail();
		try {
			this.password=Functionalities.getMd5(admin.getPassword());
		} catch (NoSuchAlgorithmException e) {
			this.password=admin.getPassword();
		}
		this.name=admin.getName();
		this.surname=admin.getSurname();
		this.role=admin.getRole();
	}
	
	/**
	 * Instantiates a new admin response.
	 */
	public AdminResponse() {
		
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
	 * @throws NoSuchAlgorithmException no md5 function working
	 */
	public void setPassword(String password) throws NoSuchAlgorithmException {
		try {
			this.password = Functionalities.getMd5(password);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
