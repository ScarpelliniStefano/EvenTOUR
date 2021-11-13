package com.scarcolo.eventour.controller.event;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.service.event.EventService;


@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	private EventService eventService;
	 @PostMapping("/events")
	    public ResponseEntity<EventResponse> addEvent(@RequestBody AddEventRequest request) throws Exception{
	      return eventService.add(request);
	    }

	   
	    @PutMapping("/events")
	    public ResponseEntity<EventResponse> updateEvent(@RequestBody EditEventRequest request) throws Exception{
	        return eventService.update(request);
	    }


	   
	    @GetMapping("/events/{id}")
	    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    @GetMapping("/events")
	    public ResponseEntity<List<EventResponse>> getAllEvents(){
	        return eventService.getAll();
	    }

	   
	    @DeleteMapping("/events/{id}")
	    public boolean deleteEventById(@RequestParam String id){
	        return eventService.delete(id);
	    }

}
