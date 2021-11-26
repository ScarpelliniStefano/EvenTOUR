package com.scarcolo.eventour.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;

public class EventManResponse {
	@JsonProperty("id")
	private String  id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("types")
	private String[] types;
	@JsonProperty("dataOra")
	private LocalDateTime dataOra;
	@JsonProperty("managerId")
	private String managerId;
	@JsonProperty("urlImage")
	private String urlImage;
	@JsonProperty("totSeat")
	private Integer totSeat;
	@JsonProperty("freeSeat")
	private Integer freeSeat;
	@JsonProperty("price")
	private Float price;
	@JsonProperty("manager")
	private Manager[] manager;
	
	
	
	public EventManResponse(String id, String title, String description, Location location, String[] types,
			LocalDateTime dataOra, String managerId, String urlImage, Integer totSeat, Integer freeSeat, Float price,
			Manager[] manager) {
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
		this.manager = manager;
	}
	
	public EventManResponse() {
		
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Manager[] getManager() {
		return manager;
	}
	public void setManager(Manager[] manager) {
		this.manager = manager;
	}
	
	

	
}
