package com.scarcolo.eventour.service.event;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.repository.event.EventRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// TODO: Auto-generated Javadoc
/**
 * The Class EventService.
 */
@Service
public class EventService {

    /** The event repository. */
    @Autowired
    private EventRepository eventRepository;

   
    /**
     * Add a event.
     *
     * @param request the request
     * @return the response entity
     */
    public ResponseEntity<EventResponse> add(AddEventRequest request){
        Event event;
		try {
			event = eventRepository.save(new Event(request));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }


   
    /**
     * Get a event by id.
     *
     * @param id the id
     * @return the event by id
     */
    public ResponseEntity<EventResponse> getById(String id) {
    	Optional<Event> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(new EventResponse(eventData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

	/**
	 * Get all events.
	 *
	 * @param page the page
	 * @param size the size
	 * @param ordered the order
	 * @param param the parameter to order on
	 * @return all events ordered, if necessary, and divided by page of size
	 */
	public ResponseEntity<Map<String, Object>> getAll(int page, int size, String ordered,String param) {
		try {
			List<Event> events = new ArrayList<>();
			
			Pageable paging = PageRequest.of(page, size);
			
			Page<Event> pageEvents;
			if(param==null)
					pageEvents=eventRepository.findAllFuture(paging);
			else {
				if(ordered.equalsIgnoreCase("desc")) {
					if(param.equals("dataOra")) {
						paging = PageRequest.of(page, size,Sort.by("dataOra").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else if(param.equals("loc")) {
						paging = PageRequest.of(page, size,Sort.by("location.regione","location.provincia","location.city").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else { 
						paging = PageRequest.of(page, size,Sort.by("dataOra").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}
				}else {
					if(param.equalsIgnoreCase("dataOra")) {
						paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else if(param.equals("loc")) {
						paging = PageRequest.of(page, size,Sort.by("location.regione","location.provincia","location.city").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else {
						paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}
				}
			} 
			events=pageEvents.getContent();	
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
		    response.put("currentPage", pageEvents.getNumber());
		    response.put("totalItems", pageEvents.getTotalElements());
		    response.put("totalPages", pageEvents.getTotalPages());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Gets all events by id manager.
	 *
	 * @param page the page
	 * @param size the size
	 * @param id the id manager
	 * @return page required of size with the events
	 */
	public ResponseEntity<Map<String, Object>> getByIdMan(int page, int size, String id) {
		try {
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			Page<Event> pageEvents;
			pageEvents=eventRepository.findByManagerId(new ObjectId(id),paging);
			List<Event> events=pageEvents.getContent();
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) {
				eventR.add(new EventResponse(event));
			}
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
			response.put("currentPage", pageEvents.getNumber());
			response.put("totalItems", pageEvents.getTotalElements());
			response.put("totalPages", pageEvents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
     * Delete a event, its ticket inspector and all bookings to the event.
     *
     * @param id the id of event
     * @return true, if successful
     */
	public boolean delete(String id) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return false;
        }
        eventRepository.deleteById(optionalEvent.get().getId());
        return true;
	}

}
