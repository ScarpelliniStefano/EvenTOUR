/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.PartitaIVAFunctions;
import com.scarcolo.eventour.model.Account;


/**
 * @author stefa
 *
 */

public class ManagerResponse extends Account{
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("surname")
	private String surname;
	
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	
	@JsonProperty("residence")
	private String residence;
	
	@JsonProperty("codicePIVA")
	private String codicePIVA;
	
	@JsonProperty("RagioneSociale")
	private String ragioneSociale;
	
	@JsonProperty("mail")
	private String mail;
	
	@JsonProperty("password")
	private String password;

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	public ManagerResponse(Manager manager) throws Exception {
		this.id=manager.getId();
		this.mail=manager.getEmail();
		this.password=manager.getPassword();
		this.codicePIVA=manager.getCodicePIVA();
		this.ragioneSociale=manager.getRagioneSociale();
		this.name=manager.getName();
		this.surname=manager.getSurname();
		this.dateOfBirth=manager.getDateOfBirthLocal();
		this.residence=manager.getResidence();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getCodicePIVA() {
		return codicePIVA;
	}

	public void setCodicePIVA(String codicePIVA) {
		this.codicePIVA = codicePIVA;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
