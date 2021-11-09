/**
 * 
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import functions.BootstrapSingleton;
import functions.TypesE;
import manager.Manager;

/**
 * @author stefa
 *
 */
@Document(collection = "events")
public class Event {
	
	@Id
	private Integer id;
	
	private String title;
	private String description;
	private String location;
	private ArrayList<TypesE> types;
	private String[] dateTimeEvent = new String[4];
	private Manager manager;
	private String urlimg;
	private String[] Seat = new String[2];
	
	/**
	 * @return the id
	 */
	protected Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	protected void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	protected String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	protected void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	protected String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the location
	 */
	protected String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	protected void setLocation(String location) {
		this.location = location;
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
	 * @return the dateTimeEvent
	 */
	public String[] getDateTimeEvent() {
		return dateTimeEvent;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return dateTimeEvent[0];
	}
	
	/**
	 * @return the year
	 */
	public String getMonth() {
		return dateTimeEvent[1];
	}
	
	/**
	 * @return the year
	 */
	public String getDay() {
		return dateTimeEvent[2];
	}
	
	/**
	 * @return the year
	 */
	public String getTime() {
		return dateTimeEvent[3];
	}
	
	/**
	 * @return the year
	 * @throws ParseException 
	 */
	public Date getDateEvent() throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg - hh:mm");
		return df.parse(dateTimeEvent[0]+
							"/" + dateTimeEvent[1]+
							"/" + dateTimeEvent[2]+
							" - " + dateTimeEvent[3]);
	}
	/**
	 * @param dateTimeEvent the dateTimeEvent to set
	 */
	public void setDateTimeEvent(String[] dateTimeEvent) {
		this.dateTimeEvent = dateTimeEvent;
	}
	/**
	 * @return the dateOfBirth
	 */
	protected String getDateTime() {
		return dateTimeEvent[0]+
				"/" + dateTimeEvent[1]+
				"/" + dateTimeEvent[2]+
				" - " + dateTimeEvent[3];
	}
	
	/**
	 * @param date the date (format gg/mm/aaaa) to set
	 * @throws ParseException 
	 */
	protected void setDateTimeEvent(String date) throws ParseException {
		DateFormat df=new SimpleDateFormat("gg/MM/aaaa");
		df.setLenient(false);
		Date dataProv=df.parse(date);
		dateTimeEvent[0] = dataProv.toString();
		
		
	}
	
	/**
	 * @return the manager
	 */
	protected Manager getManager() {
		return manager;
	}
	/**
	 * @param manager the manager to set
	 */
	protected void setManager(Manager manager) {
		this.manager = manager;
	}
	/**
	 * @return the urlimg
	 */
	protected String getUrlimg() {
		return urlimg;
	}
	/**
	 * @param urlimg the urlimg to set
	 */
	protected void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	/**
	 * @return the seat
	 */
	protected String[] getSeat() {
		return Seat;
	}
	/**
	 * @param seat the seat to set
	 */
	protected void setSeat(String[] seat) {
		Seat = seat;
	}
}
