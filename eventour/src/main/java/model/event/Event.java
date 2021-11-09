/**
 * 
 */
package model.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import functions.BootstrapSingleton;
import functions.TypesE;
import model.manager.Manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



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
	private String[] dateTimeEvent=new String[4];
	private Manager manager;
	private String urlimg;
	private String[] seat=new String[2];
	
	
	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param location
	 * @param types
	 * @param dateTimeEvent
	 * @param manager
	 * @param urlimg
	 * @param seat ([0] for total seats, [1] for free seat)
	 * @throws ParseException 
	 */
	public Event(Integer id, String title, String description, String location, ArrayList<TypesE> types,
			String[] dateTimeEvent, Manager manager, String urlimg, String[] seat) throws ParseException {
		super();
		setId(id);
		setTitle(title);
		setDescription(description);
		setLocation(location);
		setTypes(types);
		setDateTimeEvent(dateTimeEvent);
		setManager(manager);
		setUrlimg(urlimg);
		setSeat(seat);
	}
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
	 * @throws ParseException 
	 */
	public void setDateTimeEvent(String[] dateTimeEvent) throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg hh:mm");
		df.setLenient(false);
		Date dataProv=df.parse(dateTimeEvent[0]+"/"+dateTimeEvent[1]+"/"+dateTimeEvent[2]+" "+dateTimeEvent[3]);
		if(dataProv.after(new Date())) {
			this.dateTimeEvent = dateTimeEvent;
		}
		
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
		String[] provDate=new String[4];
		provDate[2]=date.substring(0,2);
		provDate[1] = date.substring(3,2);
		provDate[0] = date.substring(6,4);
		provDate[3] = "00:00";
		setDateTimeEvent(provDate);
	}
	
	/**
	 * @param date the date (format gg/mm/aaaa) to set
	 * @throws ParseException 
	 */
	protected void setDateTimeEvent(String date,String time) throws ParseException {
		String[] provDate=new String[4];
		provDate[2] = date.substring(0,2);
		provDate[1] = date.substring(3,2);
		provDate[0] = date.substring(6,4);
		provDate[3] = time;
		setDateTimeEvent(provDate);
	}
	
	/**
	 * @return the data
	 * @throws ParseException 
	 */
	protected Date getData() throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg hh:mm");
		df.setLenient(false);
		return df.parse(dateTimeEvent[0]+"/"+dateTimeEvent[1]+"/"+dateTimeEvent[2]+" "+dateTimeEvent[3]);
	}
	/**
	 * @param data the data to set
	 * @throws ParseException 
	 */
	protected void setData(Date data) throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg hh:mm");
		df.setLenient(false);
		String dataProv=df.format(dateTimeEvent[0]+"/"+dateTimeEvent[1]+"/"+dateTimeEvent[2]+" "+dateTimeEvent[3]);
		String[] provDate=new String[4];
		provDate[2] = dataProv.substring(0,2);
		provDate[1] = dataProv.substring(3,2);
		provDate[0] = dataProv.substring(6,4);
		provDate[3] = dataProv.substring(11, 5);
		setDateTimeEvent(provDate);
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
		return this.seat;
	}
	/**
	 * @param seat the seat to set
	 */
	protected void setSeat(String[] seat) {
		this.seat = seat;
	}
	
	/**
	 * @param totseat the tot seat to set
	 */
	protected void setSeatTot(Integer totseat) {
		seat[0] = totseat.toString();
	}
	
	/**
	 * @param seat the seat to set
	 */
	protected void setSeatFree(Integer freeseat) {
		seat[1] = freeseat.toString();
	}
	
	/**
	 * @param totseat the tot seat to set
	 */
	protected void setSeat(Integer totseat,Integer freeseat) {
		seat[0] = totseat.toString();
		seat[1] = freeseat.toString();
	}
	
	/**
	 * @param seat the seat to set
	 */
	protected void setSeatFree(String freeseat) {
		seat[1] = freeseat;
	}
}
