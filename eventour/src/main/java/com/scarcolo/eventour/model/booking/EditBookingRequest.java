package com.scarcolo.eventour.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditBookingRequest extends AddBookingRequest{

    @JsonProperty("id")
    public String  id;

}
