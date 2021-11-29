package com.scarcolo.eventour.controller.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.CheckBookingRequest;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.service.booking.BookingService;

// TODO: Auto-generated Javadoc
/**
 * The Class BookingController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class BookingController {
	
	/** The booking service. */
	@Autowired
	private BookingService bookingService;
	 
	/**
	 * Adds the booking.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping("/bookings")
	public ResponseEntity<Object> addBooking(@RequestBody AddBookingRequest request){
	      return bookingService.add(request);
	}

	    
	   /**
   	 * Gets the booking by user and event.
   	 *
   	 * @param id the id
   	 * @param idE the id E
   	 * @return the booking by user and event
   	 */
   	@GetMapping("/bookings/user/{id}/event/{idEv}")
	    public ResponseEntity<List<EventBookedResponse>> getBookingByUserAndEvent(@PathVariable("id") String id, @PathVariable("idEv") String idE){
	        return bookingService.getByIdUser(id);
	    }

	   
	    /**
    	 * Delete booking by id.
    	 *
    	 * @param id the id
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/bookings/{id}")
	    public boolean deleteBookingById(@PathVariable("id") String id){
	        return bookingService.delete(id);
	    }
	    
	    /**
    	 * Gets the check booking.
    	 *
    	 * @param request the request
    	 * @return the check booking
    	 */
    	@PostMapping("/bookings/check")
	    public ResponseEntity<String> getCheckBooking(@RequestBody CheckBookingRequest request){
	        return bookingService.getCheck(request.bookingNr,request.eventId);
	    }

}
