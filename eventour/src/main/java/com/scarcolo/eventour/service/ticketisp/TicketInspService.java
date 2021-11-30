package com.scarcolo.eventour.service.ticketisp;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
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

   
    /**
     * Add a ticket inspector.
     *
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity<TicketInspResponse> add(AddTicketInspRequest request) throws Exception {
        TicketInsp ticketInsp = ticketInspRepository.save(new TicketInsp(request));
        return new ResponseEntity<>(new TicketInspResponse(ticketInsp), HttpStatus.OK);
    }

  
    /**
     * Update a ticket inspector.
     *
     * @param request the request
     * @return the response entity
     */
    public ResponseEntity<TicketInspResponse> update(EditTicketInspRequest request) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(request.id);
        if (optionalTicketInsp.isEmpty()) {
            return null;
        }
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
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(id);
        if (optionalTicketInsp.isEmpty()) {
            return false;
        }
        ticketInspRepository.deleteById(optionalTicketInsp.get().getId());
        return true;
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
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	
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
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 
	
}
