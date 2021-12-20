package com.scarcolo.eventour.controller.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.CheckBookingRequest;
import com.scarcolo.eventour.model.booking.EditBookingRequest;
import com.scarcolo.eventour.model.booking.PaymentRequest;
import com.scarcolo.eventour.model.booking.ReviewBookingRequest;
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.user.UserBookedResponse;
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
	 * @param request the request of new booking
	 * @return the response entity
	 */
	@PostMapping("/bookings")
	public ResponseEntity<Object> addBooking(@RequestBody AddBookingRequest request){
	      return bookingService.add(request);
	}

	   
    /**
	 * Update booking.
	 *
	 * @param request the request of booking to update
	 * @return the response entity with booking updated
	 */
	@PutMapping("/bookings")
    public ResponseEntity<Booking> updateBooking(@RequestBody EditBookingRequest request){
        return bookingService.update(request);
    }

    
   
    /**
	 * Gets the booking by id.
	 *
	 * @param id the id of booking
	 * @return the booking by id
	 */
	@GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") String id){
        return bookingService.getById(id);
    }
    
    /**
	 * Gets the booking by id, all details of user and events.
	 *
	 * @param id the id
	 * @return the booking by id, with all details of booking's user and event
	 */
	@GetMapping("/bookings/{id}/all")
    public ResponseEntity<UserEventBookedResponse> getBookingByIdAll(@PathVariable("id") String id){
        return bookingService.getByIdDetails(id);
    }
    
    /**
	 * Get all bookings by id user.
	 *
	 * @param page the page
	 * @param size the size
	 * @param id the id of the user
	 * @return the booking by id user
	 */
	@GetMapping("/bookings/u/{id}")
    public ResponseEntity<List<EventBookedResponse>> getBookingByIdUser(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
        return bookingService.getByIdUser(page,size,id,'n');
    }
	
	/**
     * Get all future booking by id user .
     *
     * @param page the page
     * @param size the size
     * @param id the id of the user
     * @return the booking by id user
     */
	@GetMapping("/bookings/u/{id}/f")
    public ResponseEntity<List<EventBookedResponse>> getBookingByIdUserFuture(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
        return bookingService.getByIdUser(page,size,id,'f');
    }
	
	/**
     * Get all bookings past by id user .
     *
     * @param page the page
     * @param size the size
     * @param id the id of the user
     * @return the booking by id user
     */
	@GetMapping("/bookings/u/{id}/p")
    public ResponseEntity<List<EventBookedResponse>> getBookingByIdUserPast(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@PathVariable("id") String id){
        return bookingService.getByIdUser(page,size,id,'p');
    }
    
    /**
	 * Get all booking by id event.
	 *
	 * @param id the id of event
	 * @return the booking by id event
	 */
	@GetMapping("/bookings/event/{id}")
    public ResponseEntity<List<UserBookedResponse>> getBookingByIdEvent(@PathVariable("id") String id){
        return bookingService.getByIdEvent(id);
    }
    
	  /**
   	 * Gets the booking by userId and eventId.
   	 *
   	 * @param id the idUser
   	 * @param idE the idEvent
   	 * @return the booking by user and event
   	 */
	@GetMapping("/bookings/user/{id}/event/{idEv}")
    public ResponseEntity<List<EventBookedResponse>> getBookingByUserAndEvent(@PathVariable("id") String id, @PathVariable("idEv") String idE){
        return bookingService.getByUserAndEvent(id,idE);
    }

    /**
	 * Gets all bookings.
	 *
	 * @return all bookings
	 */
	@GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings(){
        return bookingService.getAll();
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
	 * Check booking.
	 *
	 * @param request the request of checking
	 * @return the check booking
	 */
	@PostMapping("/bookings/check")
    public ResponseEntity<String> getCheckBooking(@RequestBody CheckBookingRequest request){
        return bookingService.getCheck(request.bookingNr,request.eventId);
    }
	
	/**
	 * Set the review of a booking.
	 *
	 * @param request the request with booking toi review
	 * @return the operation done
	 */
	@PostMapping("/bookings/review")
    public ResponseEntity<String> setReviewBooking(@RequestBody ReviewBookingRequest request){
        return bookingService.setReview(request.bookingNr,request.review);
    }
	
	/**
     * Do payment.
     *
     * @param request the request of payment of a booking
     * @return the response entity
     */
    @PostMapping("/bookings/payment")
	public ResponseEntity<String> doPayment(@RequestBody PaymentRequest request){
	      return bookingService.checkerPayment("USER",request);
	}


}