/**
 * 
 */
package com.scarcolo.eventour.model.user;


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

public class UserResponse extends Account{
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	@JsonProperty("residence")
	private String residence;
	@JsonProperty("types")
	private String[] types;
	@JsonProperty("mail")
	private String mail;
	@JsonProperty("password")
	private String password;

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	public UserResponse(User user){
		this.id=user.getId();
		this.mail=user.getEmail();
		this.password=user.getPassword();
		this.name=user.getName();
		this.surname=user.getSurname();
		this.dateOfBirth=user.getDateOfBirthLocal();
		this.residence=user.getResidence();
		this.types=user.getTypes();
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
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
