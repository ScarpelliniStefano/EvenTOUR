/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;





// TODO: Auto-generated Javadoc
/**
 * The Class EventResponse.
 *
 * @author stefa
 */

public class EventResponse {
	
	/** The id. */
	@JsonProperty("id")
	public String  id;
	
	/** The title. */
	@JsonProperty("title")
	public String title;
	
	/** The description. */
	@JsonProperty("description")
	public String description;
	
	/** The location. */
	@JsonProperty("location")
	public Location location;
	
	/** The types. */
	@JsonProperty("types")
	public String[] types;
	
	/** The data ora. */
	@JsonProperty("dataOra")
	public LocalDateTime dataOra;
	
	/** The manager id. */
	@JsonProperty("managerId")
	public String managerId;
	
	/** The url image. */
	@JsonProperty("urlImage")
	public String urlImage;
	
	/** The tot seat. */
	@JsonProperty("totSeat")
	public Integer totSeat;
	
	/** The free seat. */
	@JsonProperty("freeSeat")
	public Integer freeSeat;
	
	/** The price. */
	@JsonProperty("price")
	public Double price;
	
	/**
	 * Instantiates a new event response.
	 *
	 * @param event the event
	 */
	public EventResponse(Event event){
		this.id=event.getId();
		this.title=event.getTitle();
        this.description=event.getDescription();
        this.location=event.getLocation();
        this.types=event.getTypes();
        this.dataOra=event.getDataOra();
        this.managerId=event.getManagerId();
        this.urlImage=event.getUrlImage();
        this.totSeat=event.getTotSeat();
        this.freeSeat=event.getFreeSeat();
        this.price=event.getPrice();
    }
	
	 /**
 	 * Instantiates a new event response.
 	 */
 	public EventResponse() {
	    }

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}

	/**
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(String[] types) {
		this.types = types;
	}

	/**
	 * Gets the data ora.
	 *
	 * @return the data ora
	 */
	public LocalDateTime getDataOra() {
		return dataOra;
	}

	/**
	 * Sets the data ora.
	 *
	 * @param dataOra the new data ora
	 */
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	/**
	 * Gets the manager id.
	 *
	 * @return the manager id
	 */
	public String getManagerId() {
		return managerId;
	}

	/**
	 * Sets the manager id.
	 *
	 * @param managerId the new manager id
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	/**
	 * Gets the url image.
	 *
	 * @return the url image
	 */
	public String getUrlImage() {
		return urlImage;
	}

	/**
	 * Sets the url image.
	 *
	 * @param urlImage the new url image
	 */
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	
	/**
	 * Gets the tot seat.
	 *
	 * @return the tot seat
	 */
	public Integer getTotSeat() {
		return totSeat;
	}

	/**
	 * Sets the tot seat.
	 *
	 * @param totSeat the new tot seat
	 */
	public void setTotSeat(Integer totSeat) {
		this.totSeat = totSeat;
	}
	
	/**
	 * Gets the free seat.
	 *
	 * @return the free seat
	 */
	public Integer getFreeSeat() {
		return freeSeat;
	}

	/**
	 * Sets the free seat.
	 *
	 * @param freeSeat the new free seat
	 */
	public void setFreeSeat(Integer freeSeat) {
		this.freeSeat = freeSeat;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param double1 the new price
	 */
	public void setPrice(Double double1) {
		this.price = double1;
	}
	
	
}
