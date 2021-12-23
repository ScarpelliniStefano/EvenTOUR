/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;


// TODO: Auto-generated Javadoc
/**
 * The Class ManagerResponse.
 *
 * @author stefa
 */

public class ManagerResponse{
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The surname. */
	@JsonProperty("surname")
	private String surname;
	
	/** The date of birth. */
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	
	/** The residence. */
	@JsonProperty("residence")
	private Location residence;
	
	/** The codice PIVA. */
	@JsonProperty("codicePIVA")
	private String codicePIVA;
	
	/** The ragione sociale. */
	@JsonProperty("ragioneSociale")
	private String ragioneSociale;
	
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
	 * @throws Exception 
	 */
	public ManagerResponse(Manager manager) throws Exception{
		this.id=manager.getId();
		this.mail=manager.getMail();
		this.password=manager.getPassword();
		this.name=manager.getName();
		this.surname=manager.getSurname();
		this.dateOfBirth=Functionalities.convertToLocalDate(manager.getDateOfBirth());
		this.residence=manager.getResidence();
		this.codicePIVA = manager.getCodicePIVA();
		this.ragioneSociale=manager.getRagioneSociale();
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
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the residence.
	 *
	 * @return the residence
	 */
	public Location getResidence() {
		return residence;
	}

	/**
	 * Sets the residence.
	 *
	 * @param residence the new residence
	 */
	public void setResidence(Location residence) {
		this.residence = residence;
	}

	/**
	 * Gets the codice PIVA.
	 *
	 * @return the codice PIVA
	 */
	public String getCodicePIVA() {
		return codicePIVA;
	}

	/**
	 * Sets the codice PIVA.
	 *
	 * @param codicePIVA the new codice PIVA
	 */
	public void setCodicePIVA(String codicePIVA) {
		this.codicePIVA = codicePIVA;
	}

	/**
	 * Gets the ragione sociale.
	 *
	 * @return the ragione sociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	/**
	 * Sets the ragione sociale.
	 *
	 * @param ragioneSociale the new ragione sociale
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
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
		this.password = password;
	}
	
	
}
