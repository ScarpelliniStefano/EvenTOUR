package com.scarcolo.eventour.service.ticketisp;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// TODO: Auto-generated Javadoc
/**
 * The Class TicketInspService.
 */
@Service
public class TicketInspService {

    /** The ticket insp repository. */
    @Autowired
    private TicketInspRepository ticketInspRepository;
    
    /** The ticket insp repository. */
    @Autowired
    private EventRepository eventRepository;

   
    /**
     * Add a ticket inspector.
     *
     * @param request the request of new ticket inspector
     * @return the response entity with all data of created ticket inspector
     * @throws Exception the exception
     */
    public ResponseEntity<TicketInspResponse> add(AddTicketInspRequest request) throws Exception {
    	TicketInsp tick=new TicketInsp(request);
    	Optional<Event> optEv=eventRepository.findById(request.eventId);
    	if(optEv.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	Integer numRand=(int) (Math.random()*9999);
    	tick.setCode("TI-"+request.eventId.substring(request.eventId.length()-5)+"-"+numRand);
    	tick.setPassword(Functionalities.generatePassayPassword(8));
        TicketInsp ticketInsp = ticketInspRepository.save(tick);
        return new ResponseEntity<>(new TicketInspResponse(ticketInsp), HttpStatus.OK);
    }

  
    /**
     * Update a ticket inspector.
     *
     * @param request the request with modified data
     * @return the response entity with data modified
     */
    public ResponseEntity<TicketInspResponse> update(EditTicketInspRequest request) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(request.id);
        if (optionalTicketInsp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(request.fullName!=null) {
        	optionalTicketInsp.get().setFullName(request.fullName);
        }
        ticketInspRepository.save(optionalTicketInsp.get());
        return new ResponseEntity<>(new TicketInspResponse(optionalTicketInsp.get()), HttpStatus.OK);
    }

   
    /**
     * Gets the ticket inspector by id.
     *
     * @param id the id of ticket inspector
     * @return the ticket inspector by id
     */
    public ResponseEntity<TicketInspResponse> getById(String id) {
    	Optional<TicketInsp> ticketInspData = ticketInspRepository.findById(id);

  	  if (ticketInspData.isPresent()) {
  	    return new ResponseEntity<>(new TicketInspResponse(ticketInspData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    /**
     * Delete a ticket inspector.
     *
     * @param id the id of ticket inspector
     * @return true, if successful
     */
    public ResponseEntity<Boolean> delete(String id) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(id);
        if (optionalTicketInsp.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ticketInspRepository.deleteById(optionalTicketInsp.get().getId());
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

	/**
	 * Gets all ticket inspectors.
	 *
	 * @return all ticket inspectors
	 */
	public ResponseEntity<List<TicketInspResponse>> getAll() {
		try {
			List<TicketInsp> ticketInsps = new ArrayList<>();
			ticketInspRepository.findAll().forEach(ticketInsps::add);
			if(ticketInsps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<TicketInspResponse> ticketR= new ArrayList<>();
			for(TicketInsp ticket: ticketInsps) ticketR.add(new TicketInspResponse(ticket));
			return new ResponseEntity<>(ticketR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Get all ticket inspector by event id.
	 *
	 * @param id the id
	 * @return list of ticket inspector of a event
	 */
	public ResponseEntity<List<TicketInspResponse>> getByEventId(String id) {
		try {

			List<TicketInsp> ticketInsps=ticketInspRepository.findByEventId(new ObjectId(id));

			if(ticketInsps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<TicketInspResponse> ticketR= new ArrayList<>();
			for(TicketInsp ticket: ticketInsps) ticketR.add(new TicketInspResponse(ticket));
			return new ResponseEntity<>(ticketR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


	/**
	 * Delete all tickets from event.
	 *
	 * @param eventId the event id
	 * @return true, if successful
	 */
	public boolean deleteAllTicketsFromEvent(String eventId) {
		try {
			ticketInspRepository.deleteByEventId(new ObjectId(eventId));
			return true;
		}catch(Exception e) {
			return false;
		}
		
		
	}
	 
	
}
