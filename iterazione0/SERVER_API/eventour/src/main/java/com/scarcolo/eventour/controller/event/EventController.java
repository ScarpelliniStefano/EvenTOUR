package com.scarcolo.eventour.controller.event;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.service.event.EventService;

// TODO: Auto-generated Javadoc
/**
 * The Class EventController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EventController {
	
	/** The event service. */
	@Autowired
	private EventService eventService;
	 
 	/**
 	 * Adds the event.
 	 *
 	 * @param request the request
 	 * @return the response entity
 	 * @throws Exception the exception
 	 */
 	@PostMapping("/events")
	    public ResponseEntity<EventResponse> addEvent(@RequestBody AddEventRequest request) throws Exception{
	      return eventService.add(request);
	    }

 	@DeleteMapping("/events/{id}")
    public boolean deleteEventById(@PathVariable("id") String id){
        return eventService.delete(id);
    }
	   
	    /**
    	 * Gets the event by id.
    	 *
    	 * @param id the id
    	 * @return the event by id
    	 */
    	@GetMapping("/events/{id}")
	    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    /**
    	 * Gets the all events.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param ordered the ordered
    	 * @param param the param
    	 * @return the all events
    	 */
    	@GetMapping("/events")
	    public ResponseEntity<Map<String, Object>> getAllEvents(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size, @RequestParam(required = false) String ordered, @RequestParam(required = false) String param){
	        return eventService.getAll(page,size,ordered,param);
	    }
	    
	    
	    /**
    	 * Gets the event manager by id.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param id the id
    	 * @return the event manager by id
    	 */
    	@GetMapping("/events/manager/{id}")
	    public ResponseEntity<Map<String, Object>> getEventManagerById(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
	        return eventService.getByIdMan(page,size,id);
	    }
	    
	    

}
