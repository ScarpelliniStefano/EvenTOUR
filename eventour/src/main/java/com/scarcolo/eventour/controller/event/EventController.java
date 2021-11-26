package com.scarcolo.eventour.controller.event;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.scarcolo.eventour.model.event.Event;
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

	   
	    /*@PutMapping("/events")
	    public ResponseEntity<EventResponse> updateEvent(@RequestBody EditEventRequest request) throws Exception{
	        return eventService.update(request);
	    }*/


	   
	    @GetMapping("/events/{id}")
	    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    @GetMapping("/events")
	    public ResponseEntity<Map<String, Object>> getAllEvents(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size, @RequestParam(required = false) String ordered, @RequestParam(required = false) String param){
	        return eventService.getAll(page,size,ordered,param);
	    }
	    
	    @GetMapping("/events/data={data}")
	    public ResponseEntity<Map<String, Object>> getEventsByData(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("data") String data) throws ParseException{
	    	 return eventService.getByData(page,size,data);
	    }
	    
	    @GetMapping("/events/loc={type}/{loc}")
	    public ResponseEntity<Map<String, Object>> getEventsByLocation(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("type") String type, @PathVariable("loc") String loc){
	    	return eventService.getByLoc(page,size,loc,type);
	    }
	    
	    @GetMapping("/events/type={type}")
	    public ResponseEntity<Map<String, Object>> getEventsByType(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("type") String type){
	    	 return eventService.getByTypes(page,size,type);
	    }
	    
	    @GetMapping("/events/pref={pref}")
	    public ResponseEntity<Map<String, Object>> getEventsByPref(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("pref") String pref){
	    	 return eventService.getByPreferences(page,size,pref);
	    }
	    
	    @GetMapping("/events/manager/{id}")
	    public ResponseEntity<Map<String, Object>> getEventManagerById(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
	        return eventService.getByIdMan(page,size,id);
	    }
	    
	    @GetMapping("/events/disp")
	    public ResponseEntity<Map<String, Object>> getEventDisp(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size){
	        return eventService.getEventsDisp(page,size);
	    }
	    
	  /*  @GetMapping("/events")
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

	   
	   /* @DeleteMapping("/events/{id}")
	    public boolean deleteEventById(@RequestParam String id){
	        return eventService.delete(id);
	    }*/

}
