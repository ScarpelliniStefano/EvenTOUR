package com.scarcolo.eventour.service.booking;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;


// TODO: Auto-generated Javadoc
/**
 * The Class BookingService.
 */
@Service
public class BookingService {

    /** The booking repository. */
    @Autowired
    private BookingRepository bookingRepository;
    
    /** The event repository. */
    @Autowired
    private EventRepository eventRepository;
    
    /** The event repository. */
    @Autowired
    private UserRepository userRepository;

   
    /**
     * Adds new booking.
     *
     * @param request the request of new booking
     * @return the response entity
     */
    public ResponseEntity<Object> add(AddBookingRequest request) {
        Booking booking = bookingRepository.save(new Booking(request));
        Optional<Event> optionalEvent = eventRepository.findById(booking.getEventId());
        Optional<User> optionalUser = userRepository.findById(booking.getUserId());
        if (!optionalUser.isEmpty() && !optionalEvent.isEmpty()) {
            Event eventM=optionalEvent.get();
            if(eventM.getFreeSeat()-booking.getPrenotedSeat()>=0) {
            	eventM.setFreeSeat(eventM.getFreeSeat()-booking.getPrenotedSeat());
            	eventRepository.save(eventM);
            } else {
            	bookingRepository.delete(booking);
            	return new ResponseEntity<>("ERROR. no enough seats available.", HttpStatus.OK);
            }
            	
            
    	}else {
    		bookingRepository.delete(booking);
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	}
        return new ResponseEntity<>(booking, HttpStatus.OK);
        
    }

    /**
     * Modify.
     *
     * @param id the id
     */
    private void modify(String id) {
    	Optional<Booking> optionalBooking = bookingRepository.findById(id);
    	if (!optionalBooking.isEmpty()) {
            Booking book=optionalBooking.get();
            book.setCome(true);
            bookingRepository.save(book);
    	}
    }

  
    /**
     * Delete one booking.
     *
     * @param id the id
     * @return true, if successful
     */
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


    /**
	 * Gets all booking by id user.
	 *
	 * @param id the id user
	 * @return bookings by id user
	 */
	public ResponseEntity<List<EventBookedResponse>> getByIdUser(String id) {
		try {
			Optional<User> userOpt=userRepository.findById(id);
			if(userOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserId(new ObjectId(id));
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets all booking by id user and id event.
	 *
	 * @param id the id user
	 * @return bookings by id user
	 */
	public ResponseEntity<List<EventBookedResponse>> getByIdUserEvent(String id,String idE) {
		try {
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserIdAndEventId(id,idE);
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			if(eventR.isEmpty()) return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets if user have a booking.
	 *
	 * @param idBooking the id booking
	 * @param idEvent the id event
	 * @return the check
	 */
	public ResponseEntity<String> getCheck(String idBooking,String idEvent) {
    	Optional<Booking> bookingData = bookingRepository.findById(idBooking);
  	  	if (bookingData.isPresent()) {
  	  		if(bookingData.get().getEventId().equalsIgnoreCase(idEvent)) {
  	  			modify(idBooking);
  	  			return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
  	  		}else
  	  			return new ResponseEntity<>("ACCESS REFUSED", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>("INVALID BOOKING CODE",HttpStatus.OK);
  	  	}
    }

}
