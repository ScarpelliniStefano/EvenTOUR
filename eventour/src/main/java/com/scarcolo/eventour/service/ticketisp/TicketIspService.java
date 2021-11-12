package com.scarcolo.eventour.service.ticketisp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.ticketisp.AddTicketIspRequest;
import com.scarcolo.eventour.model.ticketisp.EditTicketIspRequest;
import com.scarcolo.eventour.model.ticketisp.TicketIsp;
import com.scarcolo.eventour.repository.ticketisp.TicketIspRepository;


@Service
public class TicketIspService {

	@Autowired
    private TicketIspRepository eventRepository;
	
    public ResponseEntity<TicketIsp> add(AddTicketIspRequest request) throws Exception {
        TicketIsp event = eventRepository.save(new TicketIsp(request));
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

  
    public ResponseEntity<TicketIsp> update(EditTicketIspRequest request) {
        Optional<TicketIsp> optionalTicketIsp = eventRepository.findById(request.id);
        if (optionalTicketIsp.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalTicketIsp.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<TicketIsp> getById(String id) {
    	Optional<TicketIsp> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    public boolean delete(String id) {
        Optional<TicketIsp> optionalTicketIsp = eventRepository.findById(id);
        if (optionalTicketIsp.isEmpty()) {
            return false;
        }
        eventRepository.deleteById(optionalTicketIsp.get().getId().toString());
        return true;
    }

	public ResponseEntity<List<TicketIsp>> getAll() {
		try {
			List<TicketIsp> events = new ArrayList<>();
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
