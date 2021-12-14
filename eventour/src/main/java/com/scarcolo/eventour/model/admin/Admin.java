/**
 * 
 */
package com.scarcolo.eventour.model.admin;


import javax.mail.internet.AddressException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;


// TODO: Auto-generated Javadoc
/**
 * The Class Manager.
 *
 * @author stefa
 */
@Document(collection = "managers")
public class Admin{
	
	/** The id. */
	@Id
	private String id;
	
	/** The mail. */
	private String mail;
	
	/** The password. */
	private String password;
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;


	/**
	 * Instantiates a new manager.
	 *
	 * @param request the request
	 * @throws Exception the exception
	 */
	public Admin(AddAdminRequest request) throws Exception{
		this.setMail(request.mail);
		this.setPassword(request.password);
		this.setName(request.name);
		this.setSurname(request.surname);
	}
	
	/**
	 * Instantiates a new manager.
	 */
	public Admin() {
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
	 * @throws AddressException the address exception
	 */
	public void setMail(String mail) throws AddressException {
		boolean res=Functionalities.isValidEmailAddress(mail);
		if(res) {
			this.mail=mail;
		}else {
			throw new AddressException();
		}
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
		this.password = password;
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
	 * @param name the name to set
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
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	

	
	
	
}
