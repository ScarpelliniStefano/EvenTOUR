package com.scarcolo.eventour.model.booking;


import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckBookingRequest.
 */
public class PaymentRequest {
	
	/**  The id user. */
	@JsonProperty("idUser")
	public String idUser;
	
	/**  The card number. */
	@JsonProperty("cardNr")
	public String cardNr;
	
	/**  The card number. */
	@JsonProperty("cardName")
	public String cardName;
	
	/**  The autorization code. */
	@JsonProperty("authNr")
	public String authNr;
	
	/**  The autorization code. */
	@JsonProperty("date")
	public String dateScad;
	
	/**  The autorization code. */
	@JsonProperty("amount")
	public Double amount;
}
