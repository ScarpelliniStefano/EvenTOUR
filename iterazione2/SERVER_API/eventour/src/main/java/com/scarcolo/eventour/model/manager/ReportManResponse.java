package com.scarcolo.eventour.model.manager;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.Booking;

// TODO: Auto-generated Javadoc
/**
 * The Class TicketManResponse.
 */
public class ReportManResponse {
	
	/** The id. */
	@JsonProperty("id")
	private String  id;
	
	/** The title. */
	@JsonProperty("title")
	private String title;
	
	/** The description. */
	@JsonProperty("description")
	private String description;
	
	/** The location. */
	@JsonProperty("location")
	private Location location;
	
	/** The types. */
	@JsonProperty("types")
	private String[] types;
	
	/** The data ora. */
	@JsonProperty("dataOra")
	private LocalDateTime dataOra;
	
	/** The manager id. */
	@JsonProperty("managerId")
	private String managerId;
	
	/** The url image. */
	@JsonProperty("urlImage")
	private String urlImage;
	
	/** The tot seat. */
	@JsonProperty("totSeat")
	private Integer totSeat;
	
	/** The free seat. */
	@JsonProperty("freeSeat")
	private Integer freeSeat;
	
	/** The price. */
	@JsonProperty("price")
	private Double price;
	
	/** The bookings. */
	@JsonProperty("booking")
	private Booking[] booking;
	
	
	
	/**
	 * Instantiates a new event man response.
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
	 * @param booking the bookings
	 */
	public ReportManResponse(String id, String title, String description, Location location, String[] types,
			LocalDateTime dataOra, String managerId, String urlImage, Integer totSeat, Integer freeSeat, Double price,
			Booking[] booking) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.types = types;
		this.dataOra = dataOra;
		this.managerId = managerId;
		this.urlImage = urlImage;
		this.totSeat = totSeat;
		this.freeSeat = freeSeat;
		this.price = price;
		this.booking = booking;
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
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the booking.
	 *
	 * @return the booking
	 */
	public Booking[] getBooking() {
		return booking;
	}

	/**
	 * Sets the booking.
	 *
	 * @param booking the new booking
	 */
	public void setBooking(Booking[] booking) {
		this.booking = booking;
	}


	
	
	

	
}
