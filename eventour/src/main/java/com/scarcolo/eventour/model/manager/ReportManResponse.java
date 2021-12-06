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

	public void setPrice(Double price) {
		this.price = price;
	}

	public Booking[] getBooking() {
		return booking;
	}

	public void setBooking(Booking[] booking) {
		this.booking = booking;
	}


	
	
	

	
}