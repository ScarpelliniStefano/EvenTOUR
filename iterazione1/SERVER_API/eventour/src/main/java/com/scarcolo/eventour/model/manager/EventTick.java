package com.scarcolo.eventour.model.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class EventTick.
 */
public class EventTick {
	
	/** The ticket insps. */
	@JsonProperty("ticketInsps")
	private TicketInspResponse ticketInsps[];
}
