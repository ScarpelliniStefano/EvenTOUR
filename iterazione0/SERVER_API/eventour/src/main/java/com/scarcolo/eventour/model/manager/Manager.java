/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.PartitaIVAFunctions;
import com.scarcolo.eventour.model.Location;


// TODO: Auto-generated Javadoc
/**
 * The Class Manager.
 *
 * @author stefa
 */
@Document(collection = "managers")
public class Manager{
	
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
	
	/** The date of birth. */
	private Date dateOfBirth;
	
	/** The residence. */
	private Location residence;
	
	/** The codice PIVA. */
	private String codicePIVA;
	
	/** The ragione sociale. */
	private String ragioneSociale;

	/**
	 * Instantiates a new manager.
	 *
	 * @param request the request
	 * @throws Exception the exception
	 */
	public Manager(AddManagerRequest request) throws Exception{
		if(!Functionalities.isValidEmailAddress(request.mail)) throw new AddressException();
		this.mail=(Functionalities.isValidEmailAddress(request.mail)) ? request.mail : null;
		this.password=request.password;
		this.name=request.name;
		this.surname=request.surname;
		this.dateOfBirth=Functionalities.convertToDate(request.dateOfBirth);
		if(!dateOfBirth.before(new Date())) {
			throw new DateTimeException("Errore data futura");
		}
		this.residence=request.residence;
		String res=PartitaIVAFunctions.validate(request.codicePIVA);
		if(res=="ok")
			this.codicePIVA = request.codicePIVA;
		else
			throw new IllegalArgumentException(res);
		this.ragioneSociale=request.ragioneSociale;
	}
	
	/**
	 * Instantiates a new manager.
	 */
	public Manager() {
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

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	
	/**
	 * Gets the date of birth local.
	 *
	 * @return the date of birth local
	 */
	public LocalDate getDateOfBirthLocal() {
		return Functionalities.convertToLocalDate(this.dateOfBirth);
	}
	
	
	/**
	 * Sets the date of birth check.
	 *
	 * @param dateOfBirth the new date of birth check
	 * @throws Exception the exception
	 */
	private void setDateOfBirthCheck(Date dateOfBirth) throws Exception {
		if(dateOfBirth.before(new Date())) {
			this.dateOfBirth = dateOfBirth;
		}else {
			throw new DateTimeException("Errore data futura");
		}
		
	}
	
	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 * @throws Exception the exception
	 */
	public void setDateOfBirth(Date dateOfBirth) throws Exception {
		setDateOfBirthCheck(dateOfBirth);
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
	 * @param residence the residence to set
	 */
	public void setResidence(Location residence) {
		this.residence = residence;
	}
	
	/**
	 * Gets the codice PIVA.
	 *
	 * @return the codicePIVA
	 */
	public String getCodicePIVA() {
		return codicePIVA;
	}
	
	/**
	 * Sets the codice PIVA.
	 *
	 * @param codicePIVA the codicePIVA to set
	 * @throws Exception the exception
	 */
	public void setCodicePIVA(String codicePIVA) throws Exception {
		String res=PartitaIVAFunctions.validate(codicePIVA);
		if(res=="ok")
			this.codicePIVA = codicePIVA;
		else
			throw new IllegalArgumentException(res);
	}
	
	/**
	 * Gets the ragione sociale.
	 *
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	/**
	 * Sets the ragione sociale.
	 *
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	
	
	
}
