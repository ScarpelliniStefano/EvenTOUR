package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;

public class EventTick {
	@JsonProperty("ticketInsps")
	private TicketInspResponse ticketInsps[];
}
