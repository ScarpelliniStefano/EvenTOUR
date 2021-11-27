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
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.user.UserBookedResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.event.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private EventRepository eventRepository;

   
    public ResponseEntity<Object> add(AddBookingRequest request) {
        Booking booking = bookingRepository.save(new Booking(request));
        Optional<Event> optionalEvent = eventRepository.findById(booking.getEventId());
        if (!optionalEvent.isEmpty()) {
            Event eventM=optionalEvent.get();
            if(eventM.getFreeSeat()-booking.getPrenotedSeat()>=0) {
            	eventM.setFreeSeat(eventM.getFreeSeat()-booking.getPrenotedSeat());
            	eventRepository.save(eventM);
            } else {
            	bookingRepository.delete(booking);
            	return new ResponseEntity<>(new String("ERROR. no enough seats available."), HttpStatus.OK);
            }
            	
            
    	}
        return new ResponseEntity<>(booking, HttpStatus.OK);
        
    }

    private void modify(String id) {
    	Optional<Booking> optionalBooking = bookingRepository.findById(id);
    	if (!optionalBooking.isEmpty()) {
            Booking book=optionalBooking.get();
            book.setCome(true);
            bookingRepository.save(book);
    	}
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
        
        Optional<Event> optionalEvent = eventRepository.findById(optionalBooking.get().getEventId());
        if (!optionalEvent.isEmpty()) {
            Event eventM=optionalEvent.get();
            eventM.setFreeSeat(eventM.getFreeSeat()+optionalBooking.get().getPrenotedSeat());
            eventRepository.save(eventM);
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
	
	public ResponseEntity<List<EventBookedResponse>> getByUserAndEvent(String id, String idEv) {
		try {
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserAndEvent(new ObjectId(id), new ObjectId(idEv));
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> getCheck(String idBooking,String idEvent) {
    	Optional<Booking> bookingData = bookingRepository.findById(idBooking);
    	System.out.println(bookingData);
    	System.out.println(idBooking);
    	System.out.println(idEvent);
  	  	if (bookingData.isPresent()) {
  	  		if(bookingData.get().getEventId().equals(idEvent)) {
  	  			modify(idBooking);
  	  			return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
  	  		}else
  	  			return new ResponseEntity<>("ACCESS REFUSED", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  	}
    }

	public ResponseEntity<List<UserBookedResponse>> getByIdEvent(String id) {
		try {
			AggregationResults<UserBookedResponse> userA=bookingRepository.findByEventId(new ObjectId(id));
			List<UserBookedResponse> eventR=userA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	public ResponseEntity<UserEventBookedResponse> getByIdDetails(String id) {
		try {
			AggregationResults<UserEventBookedResponse> userEventA=bookingRepository.findByIdDetails(new ObjectId(id));
			List<UserEventBookedResponse> eventR=userEventA.getMappedResults();
			return new ResponseEntity<>(eventR.get(0), HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
