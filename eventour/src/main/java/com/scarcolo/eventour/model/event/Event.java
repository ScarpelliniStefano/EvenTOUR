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
	
}
