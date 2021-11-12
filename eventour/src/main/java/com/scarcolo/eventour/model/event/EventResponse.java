/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.util.Date;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;





/**
 * @author stefa
 *
 */

public class EventResponse {
	
	@JsonProperty("id")
	public String  id;
	@JsonProperty("title")
	public String title;
	@JsonProperty("description")
	public String description;
	@JsonProperty("location")
	public String location;
	@JsonProperty("types")
	public String[] types;
	@JsonProperty("dataOra")
	public LocalDateTime dataOra;
	@JsonProperty("managerId")
	public String managerId;
	@JsonProperty("urlImage")
	public String urlImage;
	@JsonProperty("totSeat")
	public Integer totSeat;
	@JsonProperty("freeSeat")
	public Integer freeSeat;
	
	public EventResponse(Event event) throws Exception {
		this.id=event.getId();
   	    this.title=event.getTitle();
        this.description=event.getDescription();
        this.location=event.getLocation();
        this.types=event.getTypes();
        this.dataOra=event.getDataOraLocal();
        this.managerId=event.getManagerId();
        this.urlImage=event.getUrlImage();
        this.totSeat=event.getTotSeat();
        this.freeSeat=event.getFreeSeat();
    }
	
	 public EventResponse() {
	    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
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

	
	public Integer getTotSeat() {
		return totSeat;
	}

	public void setTotSeat(Integer totSeat) {
		this.totSeat = totSeat;
	}
	
	public Integer getFreeSeat() {
		return freeSeat;
	}

	public void setFreeSeat(Integer freeSeat) {
		this.freeSeat = freeSeat;
	}
	
	
}
