/**
 * 
 */
package model.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.data.mongodb.core.mapping.Document;

import functions.BootstrapSingleton;
import functions.Functionalities;
import functions.Sex;
import functions.TypesE;
import model.Account;

/**
 * @author stefa
 *
 */
@Document(collection = "users")
public class User extends Account{
	private String name;
	private String surname;
	private String dateOfBirth;
	private Sex sex;
	private String residence;
	private ArrayList<TypesE> types;
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param dateOfBirth
	 * @param sex
	 * @param residence
	 * @param types
	 * @throws ParseException 
	 * @throws AddressException 
	 */
	public User(Integer id, String mail, String password, String name, String surname, String dateOfBirth, Sex sex,
			String residence, ArrayList<TypesE> types) throws ParseException, AddressException {
		super(id, password);
		setEmail(mail);
		setName(name);
		setSurname(surname);
		setDateOfBirth(dateOfBirth);
		setSex(sex);
		setResidence(residence);
		setTypes(types);
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
		DateFormat df=new SimpleDateFormat("dd/MM/aaaa");
		return df.parse(dateOfBirth);
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 * @throws ParseException 
	 */
	protected void setDateOfBirth(String dateOfBirth) throws ParseException {
		DateFormat df=new SimpleDateFormat("gg/MM/aaaa");
		Date dataProv=df.parse(dateOfBirth);
		if(dataProv.before(new Date())) {
			this.dateOfBirth = dateOfBirth;
		}
		
	}
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 * @throws ParseException 
	 */
	protected void setDateOfBirth(Date dateOfBirth) throws ParseException { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/aaaa");  
		setDateOfBirth(dateFormat.format(dateOfBirth));
	}
	
	/**
	 * @return the sex
	 */
	public String getSexString() {
		return sex == Sex.MAN ? "M" : "F";
	}
	
	/**
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex.matches("M") ? Sex.MAN : Sex.WOMAN;
	}
	
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
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
	 * @return the types
	 */
	public ArrayList<TypesE> getTypes() {
		return types;
	}
	
	/**
	 * @return the types
	 */
	public ArrayList<String> getTypesCode() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.code());
		}
		return returnlist;
	}
	
	/**
	 * @return the types
	 */
	public ArrayList<String> getTypesName() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.name().toLowerCase().replace(i.name().toLowerCase().substring(0, 1),i.name().substring(0, 1)));
		}
		return returnlist;
	}
	
	/**
	 * @return the types
	 */
	public ArrayList<String> getTypesNameComplete() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.description());
		}
		return returnlist;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<TypesE> types) {
		this.types = types;
	}
	
	/**
	 * @param types the types to set
	 */
	public void addTypes(TypesE types) {
		this.types.add(types);
	}
	
	/**
	 * @param types the types to set
	 */
	public void addTypes(String c) {
		TypesE tipoTrovato=BootstrapSingleton.lookup.get(c);
		this.types.add(tipoTrovato);
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