/**
 * 
 */
package com.scarcolo.eventour.model.manager;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


/**
 * @author stefa
 *
 */

public class ManagerResponse{
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	@JsonProperty("residence")
	private Location residence;
	@JsonProperty("codicePIVA")
	private String codicePIVA;
	@JsonProperty("ragioneSociale")
	private String ragioneSociale;
	@JsonProperty("mail")
	private String mail;
	@JsonProperty("password")
	private String password;
	

	/**
	 * 
	 * @param request
	 */
	public ManagerResponse(Manager manager){
		this.setId(manager.getId());
		this.setMail(manager.getMail());
		this.setPassword(manager.getPassword());
		this.setName(manager.getName());
		this.setSurname(manager.getSurname());
		this.setCodicePIVA(manager.getCodicePIVA());
		this.setDateOfBirth(manager.getDateOfBirthLocal());
		this.setResidence(manager.getResidence());
		this.setRagioneSociale(manager.getRagioneSociale());
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

	public Location getResidence() {
		return residence;
	}

	public void setResidence(Location residence) {
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