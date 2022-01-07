package com.scarcolo.eventour.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class EditUserRequest.
 */
public class EditUserRequest extends AddUserRequest{

    /** The id. */
    @JsonProperty("id")
    public String  id;

}
