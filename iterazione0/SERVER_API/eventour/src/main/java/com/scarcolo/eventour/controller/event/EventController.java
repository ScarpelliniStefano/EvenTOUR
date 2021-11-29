package com.scarcolo.eventour.controller.event;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	private EventService eventService;
	 @PostMapping("/events")
	    public ResponseEntity<EventResponse> addEvent(@RequestBody AddEventRequest request) throws Exception{
	      return eventService.add(request);
	    }

	   
	    @GetMapping("/events/{id}")
	    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    @GetMapping("/events")
	    public ResponseEntity<Map<String, Object>> getAllEvents(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size, @RequestParam(required = false) String ordered, @RequestParam(required = false) String param){
	        return eventService.getAll(page,size,ordered,param);
	    }
	    
	    
	    @GetMapping("/events/manager/{id}")
	    public ResponseEntity<Map<String, Object>> getEventManagerById(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
	        return eventService.getByIdMan(page,size,id);
	    }
	    
	    

}
