/**
 * 
 */
package com.scarcolo.eventour.model.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.mail.internet.AddressException;

import com.scarcolo.eventour.functions.BootstrapSingleton;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.TypesE;
import com.scarcolo.eventour.model.Account;



/**
 * @author stefa
 *
 */
public class User extends Account{
	private String name;
	private String surname;
	private LocalDateTime dateOfBirth;
	private String sex;
	private String residence;
	private String[] types;
	
	public User(AddUserRequest request) throws Exception {
        super(request.password);
        setEmail(request.mail);
        this.name=request.name;
        this.surname=request.surname;
        setDateOfBirth(request.dateOfBirth);
        setSex(request.sex);
    }
	
	public User() {
		super();
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
	protected LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}
	
	
	private void setDateOfBirthCheck(LocalDateTime dateOfBirth) throws Exception {
		if(dateOfBirth.isBefore(LocalDateTime.now())) {
			this.dateOfBirth = dateOfBirth;
		}else {
			throw new Exception("Errore data futura");
		}
		
	}
	
	public void setDateOfBirth(LocalDateTime dateOfBirth) throws Exception {
		setDateOfBirthCheck(dateOfBirth);
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex=="M" ? "M" : "F";
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
