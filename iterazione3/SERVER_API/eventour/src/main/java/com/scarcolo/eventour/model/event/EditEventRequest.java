package com.scarcolo.eventour.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class EditEventRequest.
 */
public class EditEventRequest extends AddEventRequest{

    /** The id. */
    @JsonProperty("id")
    public String  id;

}
