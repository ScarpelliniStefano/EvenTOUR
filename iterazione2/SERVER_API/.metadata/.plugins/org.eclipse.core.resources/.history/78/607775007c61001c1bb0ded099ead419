package com.scarcolo.eventour.controller.ticketisp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
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
	 * Adds the ticket insp.
	 *
	 * @param request the request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping("/ticketInsps")
	public ResponseEntity<TicketInspResponse> addTicketInsp(@RequestBody AddTicketInspRequest request) throws Exception{
	      return ticketInspService.add(request);
	}

	   
	    /**
    	 * Update ticket insp.
    	 *
    	 * @param request the request
    	 * @return the response entity
    	 */
    	@PutMapping("/ticketInsps")
	    public ResponseEntity<TicketInspResponse> updateTicketInsp(@RequestBody EditTicketInspRequest request){
	        return ticketInspService.update(request);
	    }
	    
	    /**
    	 * Gets all ticket inspectors by event id.
    	 *
    	 * @param id the id
    	 * @return the ticket insp by event id
    	 */
    	@GetMapping("/ticketInsps/event/{id}")
	    public ResponseEntity<List<TicketInspResponse>> getTicketInspByEventId(@PathVariable("id") String id){
	        return ticketInspService.getByEventId(id);
	    }
	    
	    /**
    	 * Gets all ticket inspectors by manager id.
    	 *
    	 * @param id the id
    	 * @return the ticket insp by manager id
    	 */
    	@GetMapping("/ticketInsps/manager/{id}")
	    public ResponseEntity<List<Object>> getTicketInspByManagerId(@PathVariable("id") String id){
	        return ticketInspService.getByManagerId(id);
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
	    

	    /**
    	 * Get all ticket insps.
    	 *
    	 * @return the all ticket insps
    	 */
    	@GetMapping("/ticketInsps")
	    public ResponseEntity<List<TicketInspResponse>> getAllTicketInsps(){
	        return ticketInspService.getAll();
	    }
	    
	   
	    /**
    	 * Delete ticket insp by id.
    	 *
    	 * @param id the id
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/ticketInsps/{id}")
	    public boolean deleteTicketInspById(@PathVariable("id") String id){
	        return ticketInspService.delete(id);
	    }

}
