package com.scarcolo.eventour.model.ticketinsp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditTicketInspRequest extends AddTicketInspRequest{

    @JsonProperty("id")
    public String  id;

}
