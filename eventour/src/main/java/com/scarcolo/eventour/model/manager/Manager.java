/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.PartitaIVAFunctions;
import com.scarcolo.eventour.model.Account;


/**
 * @author stefa
 *
 */
@Document(collection = "managers")
public class Manager extends Account{
	
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String residence;
	private String codicePIVA;
	private String ragioneSociale;

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	public Manager(AddManagerRequest request) throws Exception {
		super(false,request.mail, request.password);
		setEmail(request.mail);
		setName(request.name);
		setSurname(request.surname);
		setDateOfBirth(Functionalities.convertToDate(request.dateOfBirth));
		setResidence(request.residence);
		setCodicePIVA(request.codicePIVA);
		setRagioneSociale(request.ragioneSociale);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	
	public LocalDate getDateOfBirthLocal() {
		return Functionalities.convertToLocalDate(this.dateOfBirth);
	}
	
	
	private void setDateOfBirthCheck(Date dateOfBirth) throws Exception {
		if(dateOfBirth.before(new Date())) {
			this.dateOfBirth = dateOfBirth;
		}else {
			throw new Exception("Errore data futura");
		}
		
	}
	
	public void setDateOfBirth(Date dateOfBirth) throws Exception {
		setDateOfBirthCheck(dateOfBirth);
	}
	/**
	 * @return the residence
	 */
	public String getResidence() {
		return residence;
	}
	/**
	 * @param residence the residence to set
	 */
	public void setResidence(String residence) {
		this.residence = residence;
	}
	/**
	 * @return the codicePIVA
	 */
	public String getCodicePIVA() {
		return codicePIVA;
	}
	/**
	 * @param codicePIVA the codicePIVA to set
	 * @throws Exception 
	 */
	public void setCodicePIVA(String codicePIVA) throws Exception {
		String res=PartitaIVAFunctions.validate(codicePIVA);
		if(res==null)
			this.codicePIVA = codicePIVA;
		else
			throw new Exception(res);
	}
	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	/**
	 * @return the username
	 */
	public String getEmail() {
		return getUsername();
	}

	/**
	 * @param username the username to set
	 * @throws AddressException 
	 */
	public void setEmail(String mail) throws AddressException {
		boolean res=Functionalities.isValidEmailAddress(mail);
		if(res) {
			setUsername(mail);
		}else {
			throw new AddressException();
		}
			
	}
	
}
