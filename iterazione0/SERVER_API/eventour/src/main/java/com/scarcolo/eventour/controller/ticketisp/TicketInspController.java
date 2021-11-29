package com.scarcolo.eventour.controller.ticketisp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.service.ticketisp.TicketInspService;

// TODO: Auto-generated Javadoc
/**
 * The Class TicketInspController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TicketInspController {
	
	/** The ticket insp service. */
	@Autowired
	private TicketInspService ticketInspService;
	    
	    /**
    	 * Gets the ticket insp by event id.
    	 *
    	 * @param id the id
    	 * @return the ticket insp by event id
    	 */
    	@GetMapping("/ticketInsps/event/{id}")
	    public ResponseEntity<List<TicketInspResponse>> getTicketInspByEventId(@PathVariable("id") String id){
	        return ticketInspService.getByEventId(id);
	    }
	    
	   
	    /**
    	 * Gets the ticket insp by id.
    	 *
    	 * @param id the id
    	 * @return the ticket insp by id
    	 */
    	@GetMapping("/ticketInsps/{id}")
	    public ResponseEntity<TicketInspResponse> getTicketInspById(@PathVariable("id") String id){
	        return ticketInspService.getById(id);
	    }

}
