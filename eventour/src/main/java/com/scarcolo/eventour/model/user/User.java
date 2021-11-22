/**
 * 
 */
package com.scarcolo.eventour.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.BootstrapSingleton;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.TypesE;
import com.scarcolo.eventour.model.Location;



/**
 * @author stefa
 *
 */
@Document(collection = "users")
public class User{
	@Id
	private String id;
	private String username;
	private String mail;
	private String password;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String sex;
	private Location residence;
	private String[] types;
	
	public User(AddUserRequest request) throws Exception {
		this.setUsername(request.username);
        this.setEmail(request.mail);
        this.setPassword(request.password);
        this.setName(request.name);
        this.setSurname(request.surname);
        this.setDateOfBirth(Functionalities.convertToDate(request.dateOfBirth));
        this.setSex(request.sex);
        this.setResidence(request.residence);
        this.setTypes(request.types);
    }
	
	public User() {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	protected Date getDateOfBirth() {
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = (sex=="M") ? "M" : "F";
	}

	public String[] getTypes() {
		return types;
	}
	
	public String[] getTypesName() {
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
	}

	public void setTypes(String[] types) {
		this.types = types;
	}
	
	public void addType(String type) {
		String[] newArr=Arrays.copyOf(this.types, this.types.length+1);
		newArr[this.types.length+1]=type;
		this.types=newArr;
		
	}

	
	/**
	 * @return the residence
	 */
	protected Location getResidence() {
		return residence;
	}
	/**
	 * @param residence the residence to set
	 */
	protected void setResidence(Location residence) {
		this.residence = residence;
	}
	
	/**
	 * @return the username
	 */
	public String getEmail() {
		return this.mail;
	}

	/**
	 * @param username the username to set
	 * @throws AddressException 
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
