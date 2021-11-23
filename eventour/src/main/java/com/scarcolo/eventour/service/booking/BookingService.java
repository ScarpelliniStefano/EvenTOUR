package com.scarcolo.eventour.service.booking;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.EditBookingRequest;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

   
    public ResponseEntity<Booking> add(AddBookingRequest request) {
        Booking booking = bookingRepository.save(new Booking(request));
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

  
    public ResponseEntity<Booking> update(EditBookingRequest request) {
        Optional<Booking> optionalBooking = bookingRepository.findById(request.id);
        if (optionalBooking.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalBooking.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<Booking> getById(String id) {
    		Optional<Booking> bookingData = bookingRepository.findById(id);

    		if (bookingData.isPresent()) {
    			return new ResponseEntity<>(bookingData.get(), HttpStatus.OK);
    		} else {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	
    }

  
    public boolean delete(String id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isEmpty()) {
            return false;
        }
        bookingRepository.deleteById(optionalBooking.get().getId());
        return true;
    }

	public ResponseEntity<List<Booking>> getAll() {
		try {
			List<Booking> bookings = new ArrayList<>();
			bookingRepository.findAll().forEach(bookings::add);
			if(bookings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<List<EventBookedResponse>> getByIdUser(String id) {
		try {
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserId(new ObjectId(id));
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<String> getCheck(String idBooking,String idEvent) {
    	Optional<Booking> bookingData = bookingRepository.findById(idBooking);

  	  	if (bookingData.isPresent()) {
  	  		if(bookingData.get().getEventId().equals(idEvent))
  	  			return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
  	  		else
  	  			return new ResponseEntity<>("ACCESS REFUSED", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  	}
    }
}
