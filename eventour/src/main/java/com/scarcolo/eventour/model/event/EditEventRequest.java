package com.scarcolo.eventour.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditEventRequest extends AddEventRequest{

    @JsonProperty("id")
    public String  id;

}
