package com.scarcolo.eventour.service.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.repository.event.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

   
    public ResponseEntity<Event> add(AddEventRequest request) throws Exception {
        Event event = eventRepository.save(new Event(request));
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

  
    public ResponseEntity<Event> update(EditEventRequest request) {
        Optional<Event> optionalEvent = eventRepository.findById(request.id);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<Event> getById(String id) {
    	Optional<Event> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    public boolean delete(String id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return false;
        }
        eventRepository.deleteById(optionalEvent.get().getId());
        return true;
    }

	public ResponseEntity<List<Event>> getAll() {
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
}
