/**
 * 
 */
package com.scarcolo.eventour.model.user;


import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;


// TODO: Auto-generated Javadoc
/**
 * The Class UserResponse.
 *
 * @author stefa
 */

public class UserResponse{
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The surname. */
	@JsonProperty("surname")
	private String surname;
	
	/** The sex. */
	@JsonProperty("sex")
	private String sex;
	
	/** The date of birth. */
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	
	/** The residence. */
	@JsonProperty("residence")
	private Location residence;
	
	/** The types. */
	@JsonProperty("types")
	private String[] types;
	
	/** The mail. */
	@JsonProperty("mail")
	private String mail;
	
	/** The password. */
	@JsonProperty("password")
	private String password;
	
	/** The username. */
	@JsonProperty("username")
	private String username;

	/**
	 * Instantiates a new user response.
	 *
	 * @param user the user
	 */
	public UserResponse(User user){
		this.id=user.getId();
		this.username=user.getUsername();
		this.mail=user.getEmail();
		this.password=user.getPassword();
		this.name=user.getName();
		this.surname=user.getSurname();
		this.sex=user.getSex();
		this.dateOfBirth=user.getDateOfBirth();
		this.residence=user.getResidence();
		this.types=user.getTypes();
	}

	/**
	 * Instantiates a new user response.
	 */
	public UserResponse() {
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
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * Gets the types.
	 *
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}

	/**
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(String[] types) {
		this.types = types;
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
	 * @throws NoSuchAlgorithmException exception for md5 not present
	 */
	public void setPassword(String password) throws NoSuchAlgorithmException {
		this.password = Functionalities.getMd5(password);
	}

	
	
	
}
