package com.scarcolo.eventour.service.ticketisp;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
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
     * Get ticket inspector by id.
     *
     * @param id the id
     * @return the by id
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
	
	

	 
	
}
