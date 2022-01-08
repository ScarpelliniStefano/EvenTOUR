package com.scarcolo.eventour.service.ticketisp;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
import com.scarcolo.eventour.repository.manager.ManagerRepository;
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
    
    /** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	 /** The ticket insp repository. */
    @Autowired
    private EventRepository eventRepository;
   
    /**
     * Add a ticket inspector.
     *
     * @param request the request
     * @return the response entity
     */
    public ResponseEntity<TicketInspResponse> add(AddTicketInspRequest request){
    	TicketInsp tick;
		try {
			tick = new TicketInsp(request);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
    	Optional<Event> optEv=eventRepository.findById(request.eventId);
    	if(optEv.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	Integer numRand=(int) (Math.random()*9999);
    	try {
			tick.setCode("TI-"+request.eventId.substring(request.eventId.length()-5)+"-"+numRand);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
    	tick.setPassword(Functionalities.generatePassayPassword(8));
        TicketInsp ticketInsp = ticketInspRepository.save(tick);
        return new ResponseEntity<>(new TicketInspResponse(ticketInsp), HttpStatus.OK);
    }

  
    /**
     * Update a ticket inspector.
     *
     * @param request the request of update
     * @return the response entity
     */
    public ResponseEntity<TicketInspResponse> update(EditTicketInspRequest request) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(request.id);
        if (optionalTicketInsp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TicketInsp ti=optionalTicketInsp.get();
        if(request.eventId!=null) {
        	ti.setEventId(request.eventId);
        }
        if(request.fullName!=null) {
        	ti.setFullName(request.fullName);
        }
        ti=ticketInspRepository.save(ti);
        return new ResponseEntity<>(new TicketInspResponse(ti), HttpStatus.OK);
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
	 * Get all ticket inspector of a given event id.
	 *
	 * @param id the id of event
	 * @return list of ticket inspector of a event
	 */
	public ResponseEntity<List<TicketInspResponse>> getByEventId(String id) {
		try {

			List<TicketInsp> ticketInsps=ticketInspRepository.findByEventId(id);

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
	 * Gets all ticket inspector of a given manager id.
	 *
	 * @param id the id manager
	 * @return all ticket inspectors by manager id
	 */
	public ResponseEntity<List<Object>> getByManagerId(String id) {
		try {
			AggregationResults<Object> ticketInspsA=managerRepository.findAllTicketInsps(new ObjectId(id));
			List<Object> ticketInsps=ticketInspsA.getMappedResults();
			if(ticketInsps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ticketInsps, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Delete all ticket inspectors of a event.
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