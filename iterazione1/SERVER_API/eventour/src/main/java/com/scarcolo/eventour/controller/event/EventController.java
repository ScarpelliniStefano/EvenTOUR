package com.scarcolo.eventour.controller.event;


import java.text.ParseException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
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
	 	 * @param request the request with data to add
	 	 * @return the response entity with event formatted
	 	 * @throws Exception the exception if add event is not possible
	 	 */
	 	@PostMapping("/events")
	    public ResponseEntity<EventResponse> addEvent(@RequestBody AddEventRequest request) throws Exception{
	      return eventService.add(request);
	    }

	   
	    /**
    	 * Update event.
    	 *
    	 * @param request the request with data to update
    	 * @return the response entity  with event formatted
    	 * @throws Exception the exception if update event is not possible
    	 */
    	@PutMapping("/events")
	    public ResponseEntity<EventResponse> updateEvent(@RequestBody EditEventRequest request) throws Exception{
	        return eventService.update(request);
	    }


	   
	    /**
    	 * Gets the event by id.
    	 *
    	 * @param id the id of event
    	 * @return the event by id specified
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
    	 * @param ordered the order of response
    	 * @param param the param to order
    	 * @return all events ordered and divided by page
    	 */
    	@GetMapping("/events")
	    public ResponseEntity<Map<String, Object>> getAllEvents(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size, @RequestParam(required = false) String ordered, @RequestParam(required = false) String param){
	        return eventService.getAll(page,size,ordered,param);
	    }
	    
	    /**
    	 * Gets the events by data or interval of data.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param data the data/interval of data (ex. '2021-11-05' or '2021-11-05,2022-01-18')
    	 * @return the events by data
    	 * @throws ParseException the parse exception of data inserted
    	 */
    	@GetMapping("/events/data={data}")
	    public ResponseEntity<Map<String, Object>> getEventsByData(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("data") String data) throws ParseException{
	    	 return eventService.getByData(page,size,data);
	    }
	    
	    /**
    	 * Gets the events by location (regione, provincia o città).
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param type the type of location (regione, provincia o città)
    	 * @param loc the loc (name of the location)
    	 * @return the events by location
    	 */
    	@GetMapping("/events/loc={type}/{loc}")
	    public ResponseEntity<Map<String, Object>> getEventsByLocation(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("type") String type, @PathVariable("loc") String loc){
	    	return eventService.getByLoc(page,size,loc,type);
	    }
	    
	    /**
    	 * Gets the events by type or types.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param type the type/types (ex. '1.4.1,1.1.2')
    	 * @return the events by type
    	 */
    	@GetMapping("/events/type={type}")
	    public ResponseEntity<Map<String, Object>> getEventsByType(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("type") String type){
	    	 return eventService.getByTypes(page,size,type);
	    }
	    
	    /**
    	 * Gets the events by preference.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param idPref the preference
    	 * @param locInclude if include filter with region or not
    	 * @return the events by preference
    	 */
    	@GetMapping("/events/pref/{id}")
	    public ResponseEntity<Map<String, Object>> getEventsByPref(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String idPref,@RequestParam(defaultValue = "false") boolean locInclude){
	    	 return eventService.getByPreferences(page,size,idPref,locInclude);
	    }
	    
	    /**
    	 * Get all events of a manager.
    	 *
    	 * @param page the page
    	 * @param size the size
    	 * @param id the id of manager
    	 * @return the event manager by id
    	 */
    	@GetMapping("/events/manager/{id}")
	    public ResponseEntity<Map<String, Object>> getEventManagerById(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
	        return eventService.getByIdMan(page,size,id);
	    }
	    
	    /**
	  	 * Delete event by id.
	  	 *
	  	 * @param id the id
	  	 * @return true, if successful
	  	 */
	  	@DeleteMapping("/events/{id}")
	    public boolean deleteEventById(@PathVariable("id") String id){
	        return eventService.delete(id);
	    }
	  /*
	   * API OF EVENTS WITHOUT PAGE AND SIZE, BUT WITH SAME SIGNATURE
	   *   
	   *   @GetMapping("/events")
	    public ResponseEntity<List<EventResponse>> getAllEvents(@RequestParam(required = false) String ordered, @RequestParam(required = false) String param){
	        return eventService.getAll(ordered,param);
	    }
	    
	    @GetMapping("/events/data={data}")
	    public ResponseEntity<List<EventResponse>> getEventsByData(@PathVariable("data") String data) throws ParseException{
	    	 return eventService.getByData(data);
	    }
	    
	    @GetMapping("/events/loc={type}/{loc}")
	    public ResponseEntity<List<EventResponse>> getEventsByLocation(@PathVariable("type") String type, @PathVariable("loc") String loc){
	    	return eventService.getByLoc(loc,type);
	    }
	    
	    @GetMapping("/events/type={type}")
	    public ResponseEntity<List<EventResponse>> getEventsByType(@PathVariable("type") String type){
	    	 System.out.println(type);
	    	 return eventService.getByTypes(type);
	    }
	    
	    @GetMapping("/events/pref={pref}")
	    public ResponseEntity<List<EventResponse>> getEventsByPref(@PathVariable("pref") String pref){
	    	 return eventService.getByPreferences(pref);
	    }
	    
	    @GetMapping("/events/manager/{id}")
	    public ResponseEntity<List<EventResponse>> getEventManagerById(@PathVariable("id") String id){
	        return eventService.getByIdMan(id);
	    }
	    
	    @GetMapping("/events/disp")
	    public ResponseEntity<List<EventResponse>> getEventDisp(){
	        return eventService.getEventsDisp();
	    }*/

	   


}