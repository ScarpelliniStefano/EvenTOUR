/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.util.Date;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;





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
	private Location location;
	private String[] types;
	private Date dataOra;
	private ObjectId managerId;
	private String urlImage;
	private Integer freeSeat;
	private Integer totSeat;
	
	public Event(AddEventRequest request) throws Exception {
        this.setTitle(request.title);
        this.setDescription(request.description);
        this.setLocation(request.location);
        this.setTypes(request.types);
        this.setDataOra(Functionalities.convertToDate(request.dataOra));
        this.setManagerId(request.managerId);
        this.setUrlImage(request.urlImage);
        this.setTotSeat(request.totSeat);
        this.setFreeSeat(request.totSeat);
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
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
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

	public void setTypes(String[] types) {
		this.types = types;
	}
	
	public void addType(String type) {
		String[] newArr=Arrays.copyOf(this.types, this.types.length+1);
		newArr[this.types.length+1]=type;
		this.types=newArr;
		
	}

	public Date getDataOra() {
		return dataOra;
	}
	
	public LocalDateTime getDataOraLocal() {
		return Functionalities.convertToLocalDateTime(this.dataOra);
	}
	
	
	
	
	private void setDateCheck(Date dataOra) throws Exception {
		if(dataOra.after(new Date())) {
			this.dataOra = dataOra;
		}else {
			throw new Exception("Errore data passata");
		}
		
	}
	
	public void setDataOra(Date dataOra) throws Exception {
		setDateCheck(dataOra);
	}

	public String getManagerId() {
		return managerId.toHexString();
	}

	public void setManagerId(String managerId) {
		this.managerId = new ObjectId(managerId);
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
	
}
