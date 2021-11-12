/**
 * 
 */
package com.scarcolo.eventour.model.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;

import com.scarcolo.eventour.model.Account;



/**
 * @author stefa
 *
 */
public class Manager extends Account{
	private String name;
	private String surname;
	private String dateOfBirth;
	private String residence;
	private String codicePIVA;
	private String ragioneSociale;
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param dateOfBirth
	 * @param residence
	 * @param codicePIVA
	 * @param ragioneSociale
	 * @throws Exception 
	 */
	public Manager(Integer id, String mail, String password, String name, String surname, String dateOfBirth,
			String residence, String codicePIVA, String ragioneSociale) throws Exception {
		super(id, password);
		setEmail(mail);
		setName(name);
		setSurname(surname);
		setDateOfBirth(dateOfBirth);
		setResidence(residence);
		setCodicePIVA(codicePIVA);
		setRagioneSociale(ragioneSociale);
	}
	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	protected String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	protected void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the dateOfBirth
	 */
	protected String getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * @return the dateOfBirth
	 * @throws ParseException 
	 */
	protected Date getDateOfBirthDate() throws ParseException {
		DateFormat df=new SimpleDateFormat("dd/mm/aaaa");
		return df.parse(dateOfBirth);
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	protected void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	protected void setDateOfBirth(Date dateOfBirth) { 
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/aaaa");  
		this.dateOfBirth = dateFormat.format(dateOfBirth);
	}
	/**
	 * @return the residence
	 */
	protected String getResidence() {
		return residence;
	}
	/**
	 * @param residence the residence to set
	 */
	protected void setResidence(String residence) {
		this.residence = residence;
	}
	/**
	 * @return the codicePIVA
	 */
	protected String getCodicePIVA() {
		return codicePIVA;
	}
	/**
	 * @param codicePIVA the codicePIVA to set
	 * @throws Exception 
	 */
	protected void setCodicePIVA(String codicePIVA) throws Exception {
		String res=PartitaIVAFunctions.validate(codicePIVA);
		if(res==null)
			this.codicePIVA = codicePIVA;
		else
			throw new Exception(res);
	}
	/**
	 * @return the ragioneSociale
	 */
	protected String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	protected void setRagioneSociale(String ragioneSociale) {
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
