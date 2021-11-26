/**
 * 
 */
package com.scarcolo.eventour.model.user;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;


/**
 * @author stefa
 *
 */

public class UserResponse{
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
	private Location residence;
	@JsonProperty("types")
	private String[] types;
	@JsonProperty("mail")
	private String mail;
	@JsonProperty("password")
	private String password;
	@JsonProperty("username")
	private String username;

	/**
	 * 
	 * @param request
	 */
	public UserResponse(User user){
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setMail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setSex(user.getSex());
		this.setDateOfBirth(user.getDateOfBirth());
		this.setResidence(user.getResidence());
		this.setTypes(user.getTypes());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Location getResidence() {
		return residence;
	}

	public void setResidence(Location residence) {
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
