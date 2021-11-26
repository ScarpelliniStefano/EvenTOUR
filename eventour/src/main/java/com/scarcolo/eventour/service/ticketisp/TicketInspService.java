package com.scarcolo.eventour.service.ticketisp;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.CheckBookingRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.manager.TicketManResponse;
import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TicketInspService {

    @Autowired
    private TicketInspRepository ticketInspRepository;

   
    public ResponseEntity<TicketInspResponse> add(AddTicketInspRequest request) throws Exception {
        TicketInsp ticketInsp = ticketInspRepository.save(new TicketInsp(request));
        return new ResponseEntity<>(new TicketInspResponse(ticketInsp), HttpStatus.OK);
    }

  
    public ResponseEntity<TicketInspResponse> update(EditTicketInspRequest request) {
        Optional<TicketInsp> optionalTicketInsp = ticketInspRepository.findById(request.id);
        if (optionalTicketInsp.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new TicketInspResponse(optionalTicketInsp.get()), HttpStatus.OK);
    }

   
    public ResponseEntity<TicketInspResponse> getById(String id) {
    	Optional<TicketInsp> ticketInspData = ticketInspRepository.findById(id);

  	  if (ticketInspData.isPresent()) {
  	    return new ResponseEntity<>(new TicketInspResponse(ticketInspData.get()), HttpStatus.OK);
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


	/*public ResponseEntity<List<TicketInspResponse>> getByManagerId(String id) {
		try {
			List<Event> events=eventRepository.findByManagerId(id);
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<TicketInsp> ticketInsps=new ArrayList<>();
			for(Event event : events) {
				ticketInsps.addAll(ticketInspRepository.findByEventId(event.getId()));
			}
			List<TicketInspResponse> ticketR= new ArrayList<>();
			for(TicketInsp ticket: ticketInsps) ticketR.add(new TicketInspResponse(ticket));
			return new ResponseEntity<>(ticketR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	@Autowired
	private ManagerRepository managerRepository;
	
	
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

	@Autowired
	private BookingRepository bookingRepository;
	 
	
}
