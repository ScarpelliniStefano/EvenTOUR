/**
 * 
 */
package com.scarcolo.eventour.model.user;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;



// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author stefa
 */
@Document(collection = "users")
public class User{
	
	/** The id. */
	@Id
	private String id;
	
	/** The username. */
	private String username;
	
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
	
	/** The sex. */
	private String sex;
	
	/** The residence. */
	private Location residence;
	
	/** The types. */
	private String[] types;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param request the request
	 * @throws Exception the exception
	 */
	public User(AddUserRequest request) throws Exception {
		this.username=request.username;
		boolean res=Functionalities.isValidEmailAddress(request.mail);
		if(res) {
			this.mail=request.mail;
		}else {
			throw new AddressException();
		}
        this.password=request.password;
        this.name=request.name;
        this.surname=request.surname;
        if(request.dateOfBirth.isBefore(LocalDate.now())) {
			this.dateOfBirth=Functionalities.convertToDate(request.dateOfBirth);
		}else {
			throw new DateTimeException("Errore data futura");
		}
        this.sex=(request.sex.toUpperCase().contentEquals("M") || request.sex.toUpperCase().contentEquals("F"))? request.sex.toUpperCase() : "N";
        this.residence=request.residence;
        this.types=request.types;
    }
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
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
	public LocalDate getDateOfBirth() {
		return Functionalities.convertToLocalDate(this.dateOfBirth);
	}
	
	/**
	 * Sets the date of birth check.
	 *
	 * @param dateOfBirth the new date of birth check
	 * @throws DateTimeException the exception for date incorrect
	 */
	private void setDateOfBirthCheck(Date dateOfBirth) throws DateTimeException {
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
		this.sex = (sex.toUpperCase().contentEquals("M")) ? "M" : "F";
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}
	
	/*public String[] getTypesName() {
		ArrayList<String> provString=new ArrayList<String>();
		TypesE tipoTrovato;
		for(String type : types) {
			tipoTrovato=BootstrapSingleton.lookup.get(type);
			provString.add(tipoTrovato.name());
		}
		return (String[]) provString.toArray();
	}
	
	public String[] getTypesDesc() {
		ArrayList<String> provString=new ArrayList<String>();
		TypesE tipoTrovato;
		for(String type : types) {
			tipoTrovato=BootstrapSingleton.lookup.get(type);
			provString.add(tipoTrovato.description());
		}
		return (String[]) provString.toArray();
	}*/

	/**
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	/**
	 * Adds the type.
	 *
	 * @param type the type
	 */
	public void addType(String type) {
		String[] newArr=Arrays.copyOf(this.types, this.types.length+1);
		newArr[this.types.length+1]=type;
		this.types=newArr;
		
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
	 * Gets the email.
	 *
	 * @return the username
	 */
	public String getEmail() {
		return this.mail;
	}

	/**
	 * Sets the email.
	 *
	 * @param mail the new email
	 * @throws AddressException the address exception
	 */
	public void setEmail(String mail) throws AddressException {
		boolean res=Functionalities.isValidEmailAddress(mail);
		if(res) {
			this.mail=mail;
		}else {
			throw new AddressException();
		}
			
	}
}
