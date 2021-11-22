/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.PartitaIVAFunctions;
import com.scarcolo.eventour.model.Location;


/**
 * @author stefa
 *
 */
@Document(collection = "managers")
public class Manager{
	@Id
	private String id;
	private String mail;
	private String password;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Location residence;
	private String codicePIVA;
	private String ragioneSociale;

	/**
	 * 
	 * @param request
	 * @throws Exception 
	 */
	public Manager(AddManagerRequest request) throws Exception{
		this.setMail(request.mail);
		this.setPassword(request.password);
		this.setName(request.name);
		this.setSurname(request.surname);
		this.setDateOfBirth(Functionalities.convertToDate(request.dateOfBirth));
		this.setResidence(request.residence);
		this.setCodicePIVA(request.codicePIVA);
		this.setRagioneSociale(request.ragioneSociale);
	}
	
	public Manager() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) throws AddressException {
		boolean res=Functionalities.isValidEmailAddress(mail);
		if(res) {
			this.mail=mail;
		}else {
			throw new AddressException();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public Location getResidence() {
		return residence;
	}
	/**
	 * @param residence the residence to set
	 */
	public void setResidence(Location residence) {
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
		if(res=="ok")
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

	
	
	
}
