package com.scarcolo.eventour.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class EditBookingRequest.
 */
public class EditBookingRequest extends AddBookingRequest{

    /** The id. */
    @JsonProperty("id")
    public String  id;

}
