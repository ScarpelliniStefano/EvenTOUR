package com.scarcolo.eventour.service.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.repository.event.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

   
    public ResponseEntity<EventResponse> add(AddEventRequest request) throws Exception {
        Event event = eventRepository.save(new Event(request));
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

  
    public ResponseEntity<EventResponse> update(EditEventRequest request) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(request.id);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new EventResponse(optionalEvent.get()), HttpStatus.OK);
    }

   
    public ResponseEntity<EventResponse> getById(String id) {
    	Optional<Event> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(new EventResponse(eventData.get()), HttpStatus.OK);
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

	public ResponseEntity<List<EventResponse>> getAll() {
		try {
			List<Event> events = new ArrayList<>();
			eventRepository.findAll().forEach(events::add);
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
