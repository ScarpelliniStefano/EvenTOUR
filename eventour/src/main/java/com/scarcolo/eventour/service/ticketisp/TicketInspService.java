package com.scarcolo.eventour.service.ticketisp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TicketInspService {

    @Autowired
    private TicketInspRepository ticketInspRepository;

   
    public ResponseEntity<TicketInsp> add(AddTicketInspRequest request) throws Exception {
        TicketInsp ticketInsp = ticketInspRepository.save(new TicketInsp(request));
        return new ResponseEntity<>(ticketInsp, HttpStatus.OK);
    }

  
    public ResponseEntity<TicketInsp> update(EditTicketInspRequest request) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(request.id);
        if (optionalTicketInsp.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalTicketInsp.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<TicketInsp> getById(String id) {
    	Optional<TicketInsp> ticketInspData = ticketInspRepository.findById(id);

  	  if (ticketInspData.isPresent()) {
  	    return new ResponseEntity<>(ticketInspData.get(), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    public boolean delete(String id) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(id);
        if (optionalTicketInsp.isEmpty()) {
            return false;
        }
        ticketInspRepository.deleteById(optionalTicketInsp.get().getId());
        return true;
    }

	public ResponseEntity<List<TicketInsp>> getAll() {
		try {
			List<TicketInsp> ticketInsps = new ArrayList<>();
			ticketInspRepository.findAll().forEach(ticketInsps::add);
			if(ticketInsps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ticketInsps, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
