package com.scarcolo.eventour.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditUserRequest extends AddUserRequest{

    @JsonProperty("id")
    public String  id;

}
