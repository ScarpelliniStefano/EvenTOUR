/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;





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
	public Location location;
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
	@JsonProperty("price")
	public Double price;
	
	public EventResponse(Event event){
		this.id=event.getId();
		this.setTitle(event.getTitle());
        this.setDescription(event.getDescription());
        this.setLocation(event.getLocation());
        this.setTypes(event.getTypes());
        this.setDataOra(event.getDataOra());
        this.setManagerId(event.getManagerId());
        this.setUrlImage(event.getUrlImage());
        this.setTotSeat(event.getTotSeat());
        this.setFreeSeat(event.getFreeSeat());
        this.setPrice(event.getPrice());
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double double1) {
		this.price = double1;
	}
	
	
}
