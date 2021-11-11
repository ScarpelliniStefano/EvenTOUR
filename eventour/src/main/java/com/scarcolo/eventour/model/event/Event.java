/**
 * 
 */
package com.scarcolo.eventour.model.event;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;






/**
 * @author stefa
 *
 */
@Document(collection="events")
public class Event {
	
	@Id
	private String id;
	private String title;
	private String description;
	private String location;
	private Object[] types;
	private String dataAAAA;
	private String dataMM;
	private String dataGG;
	private String time;
	private String managerId;
	private String urlImage;
	private Integer freeSeat;
	private Integer totSeat;
	
	public Event(AddEventRequest request) {
        this.title=request.title;
        this.description=request.description;
        this.location=request.location;
        this.types=request.types;
        this.dataAAAA=request.dataAAAA;
        this.dataMM=request.dataMM;
        this.dataGG=request.dataGG;
        this.time=request.time;
        this.managerId=request.managerId;
        this.urlImage=request.urlImage;
        this.totSeat=request.totSeat;
        this.freeSeat=request.totSeat;
    }
	
	 public Event() {
	    }
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Object[] getTypes() {
		return types;
	}

	public void setTypes(Object[] types) {
		this.types = types;
	}

	public String getDataAAAA() {
		return dataAAAA;
	}

	public void setDataAAAA(String dataAAAA) {
		this.dataAAAA = dataAAAA;
	}

	public String getDataMM() {
		return dataMM;
	}

	public void setDataMM(String dataMM) {
		this.dataMM = dataMM;
	}

	public String getDataGG() {
		return dataGG;
	}

	public void setDataGG(String dataGG) {
		this.dataGG = dataGG;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Integer getFreeSeat() {
		return freeSeat;
	}

	public void setFreeSeat(Integer freeSeat) {
		this.freeSeat = freeSeat;
	}

	public Integer getTotSeat() {
		return totSeat;
	}

	public void setTotSeat(Integer totSeat) {
		this.totSeat = totSeat;
	}
	/*public ArrayList<TypesE> getTypes() {
		return types;
	}
	
	public ArrayList<String> getTypesCode() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.code());
		}
		return returnlist;
	}
	

	public ArrayList<String> getTypesName() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.name().toLowerCase().replace(i.name().toLowerCase().substring(0, 1),i.name().substring(0, 1)));
		}
		return returnlist;
	}
	

	public ArrayList<String> getTypesNameComplete() {
		ArrayList<String> returnlist=new ArrayList<String>();
		for (TypesE i : types) {
			returnlist.add(i.description());
		}
		return returnlist;
	}

	public void setTypes(ArrayList<TypesE> types) {
		this.types = types;
	}
	

	public void addTypes(TypesE types) {
		this.types.add(types);
	}
	

	public void addTypes(String c) {
		TypesE tipoTrovato=BootstrapSingleton.lookup.get(c);
		this.types.add(tipoTrovato);
	}

	public String[] getDateTimeEvent() {
		return dateTimeEvent;
	}

	public String getYear() {
		return dateTimeEvent[0];
	}
	

	public String getMonth() {
		return dateTimeEvent[1];
	}
	

	public String getDay() {
		return dateTimeEvent[2];
	}
	

	public String getTime() {
		return dateTimeEvent[3];
	}
	

	public Date getDateEvent() throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg - hh:mm");
		return df.parse(dateTimeEvent[0]+
							"/" + dateTimeEvent[1]+
							"/" + dateTimeEvent[2]+
							" - " + dateTimeEvent[3]);
	}

	public void setDateTimeEvent(String[] dateTimeEvent) throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg hh:mm");
		df.setLenient(false);
		Date dataProv=df.parse(dateTimeEvent[0]+"/"+dateTimeEvent[1]+"/"+dateTimeEvent[2]+" "+dateTimeEvent[3]);
		if(dataProv.after(new Date())) {
			this.dateTimeEvent = dateTimeEvent;
		}
		
	}

	protected String getDateTime() {
		return dateTimeEvent[0]+
				"/" + dateTimeEvent[1]+
				"/" + dateTimeEvent[2]+
				" - " + dateTimeEvent[3];
	}
	

	protected void setDateTimeEvent(String date) throws ParseException {
		String[] provDate=new String[4];
		provDate[2]=date.substring(0,2);
		provDate[1] = date.substring(3,2);
		provDate[0] = date.substring(6,4);
		provDate[3] = "00:00";
		setDateTimeEvent(provDate);
	}
	

	protected void setDateTimeEvent(String date,String time) throws ParseException {
		String[] provDate=new String[4];
		provDate[2] = date.substring(0,2);
		provDate[1] = date.substring(3,2);
		provDate[0] = date.substring(6,4);
		provDate[3] = time;
		setDateTimeEvent(provDate);
	}
	

	protected Date getData() throws ParseException {
		DateFormat df=new SimpleDateFormat("aaaa/MM/gg hh:mm");
		df.setLenient(false);
		return df.parse(dateTimeEvent[0]+"/"+dateTimeEvent[1]+"/"+dateTimeEvent[2]+" "+dateTimeEvent[3]);
	}

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

	protected Manager getManager() {
		return manager;
	}

	protected void setManager(Manager manager) {
		this.manager = manager;
	}

	protected String getUrlimg() {
		return urlimg;
	}

	protected void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
	}

	protected String[] getSeat() {
		return this.seat;
	}

	protected void setSeat(String[] seat) {
		this.seat = seat;
	}
	

	protected void setSeatTot(Integer totseat) {
		seat[0] = totseat.toString();
	}
	

	protected void setSeatFree(Integer freeseat) {
		seat[1] = freeseat.toString();
	}
	

	protected void setSeat(Integer totseat,Integer freeseat) {
		seat[0] = totseat.toString();
		seat[1] = freeseat.toString();
	}
	

	protected void setSeatFree(String freeseat) {
		seat[1] = freeseat;
	}*/
}
