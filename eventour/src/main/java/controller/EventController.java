package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.event.Event;
import repository.EventRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	EventRepository eventRepository;
	
	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvents(){
		try {
			List<Event> events = new ArrayList<>();
			eventRepository.findAll().forEach(events::add);
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable("id") String id) {
	  Optional<Event> eventData = eventRepository.findById(id);

	  if (eventData.isPresent()) {
	    return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	@GetMapping("events/dataAAAA={datetime}")
	public ResponseEntity<Event> getEventsByYear(@PathVariable("datetime") Integer datetime){
		try {
			List<Event> events = new ArrayList<>();
			eventRepository.findByYear(datetime).forEach(events::add);
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Event>(HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
