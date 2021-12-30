/**
 * 
 */
package com.scarcolo.eventour.model.event;



import java.util.Date;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 *
 * @author stefa
 */
@Document(collection="events")
public class EventPlus {
	
	/** The id. */
	@Id
	private String id;
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The location. */
	private Location location;
	
	/** The types. */
	private String[] types;
	
	/** The data ora. */
	private Date dataOra;
	
	/** The manager id. */
	private ObjectId managerId;
	
	/** The url image. */
	private String urlImage;
	
	/** The free seat. */
	private Integer freeSeat;
	
	/** The tot seat. */
	private Integer totSeat;
	
	/** The review sum. */
	private Integer reviewSum;
	
	/** The review number. */
	private Integer reviewTot;
	
	/** The price. */
	private Double price;
	
	/**
	 * Instantiates a new event.
	 *
	 * @param request the request of addEventRequest
	 * @throws Exception the exception of conversion of dates
	 */
	public EventPlus(AddEventRequest request) throws Exception {
        this.title=request.title;
        this.description=request.description;
        this.location=request.location;
        this.types=request.types;
        if(Functionalities.convertToDate(request.dataOra).after(new Date())) {
			this.dataOra = Functionalities.convertToDate(request.dataOra);
		}else {
			throw new DateTimeException("Errore data passata");
		}
        this.managerId=new ObjectId(request.managerId);
        this.urlImage=request.urlImage;
        this.totSeat=request.totSeat;
        this.freeSeat=request.totSeat;
        this.price=request.price;
    }
	
	 /**
 	 * Instantiates a new event.
 	 */
 	public EventPlus() {
	    }
	
	/**
	 * Instantiates a new event plus.
	 *
	 * @param id the id
	 * @param title the title
	 * @param description the description
	 * @param location the location
	 * @param types the types
	 * @param dataOra the data ora
	 * @param managerId the manager id
	 * @param urlImage the url image
	 * @param totSeat the tot seat
	 * @param freeSeat the free seat
	 * @param price the price
	 * @param reviewSum the review sum
	 * @param reviewTot the review tot
	 * @throws Exception the exception
	 */
	public EventPlus(String id, String title, String description, Location location, String[] types,
			LocalDateTime dataOra, String managerId, String urlImage, Integer totSeat, Integer freeSeat,
			Double price, Integer reviewSum, Integer reviewTot) throws Exception {
		this.id=id;
		this.title=title;
        this.description=description;
        this.location=location;
        this.types=types;
        this.dataOra=Functionalities.convertToDate(dataOra);
        this.managerId=new ObjectId(managerId);
        this.urlImage=urlImage;
        this.totSeat=totSeat;
        this.freeSeat=freeSeat;
        this.price=price;
        this.reviewSum=reviewSum;
        this.reviewTot=reviewTot;
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
	 * @param id the id to set
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
	 * @param title the title to set
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
	 * @param description the description to set
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
	 * @param location the location to set
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
	 * Adds the type.
	 *
	 * @param type the type
	 */
	public void addType(String type) {
		String[] newArr=Arrays.copyOf(this.types, this.types.length+1);
		newArr[this.types.length+1]=type;
		this.types=newArr;
		
	}

	
	/**
	 * Gets the data ora.
	 *
	 * @return the data ora
	 */
	public LocalDateTime getDataOra() {
		return Functionalities.convertToLocalDateTime(this.dataOra);
	}
	
	
	
	
	/**
	 * Sets the date check.
	 *
	 * @param dataOra the new date check
	 * @throws Exception the exception
	 */
	private void setDateCheck(Date dataOra) throws Exception {
		if(dataOra.after(new Date())) {
			this.dataOra = dataOra;
		}else {
			throw new DateTimeException("Errore data passata");
		}
		
	}
	
	/**
	 * Sets the data ora.
	 *
	 * @param dataOra the new data ora
	 * @throws Exception the exception
	 */
	public void setDataOra(Date dataOra) throws Exception {
		
		setDateCheck(dataOra);
	}

	/**
	 * Gets the manager id.
	 *
	 * @return the manager id
	 */
	public String getManagerId() {
		return managerId.toHexString();
	}

	/**
	 * Sets the manager id.
	 *
	 * @param managerId the new manager id
	 */
	public void setManagerId(String managerId) {
		this.managerId = new ObjectId(managerId);
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
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the review sum.
	 *
	 * @return the review sum
	 */
	public Integer getReviewSum() {
		return reviewSum;
	}

	/**
	 * Sets the review sum.
	 *
	 * @param reviewSum the new review sum
	 */
	public void setReviewSum(Integer reviewSum) {
		this.reviewSum = reviewSum;
	}

	/**
	 * Gets the review tot.
	 *
	 * @return the review tot
	 */
	public Integer getReviewTot() {
		return reviewTot;
	}

	/**
	 * Sets the review tot.
	 *
	 * @param reviewTot the new review tot
	 */
	public void setReviewTot(Integer reviewTot) {
		this.reviewTot = reviewTot;
	}
	
}
