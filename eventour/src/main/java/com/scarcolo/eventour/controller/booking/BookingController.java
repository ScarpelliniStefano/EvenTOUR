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
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.CheckBookingRequest;
import com.scarcolo.eventour.model.booking.EditBookingRequest;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.service.booking.BookingService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	 
	@PostMapping("/bookings")
	public ResponseEntity<Booking> addBooking(@RequestBody AddBookingRequest request){
	      return bookingService.add(request);
	}

	   
	    @PutMapping("/bookings")
	    public ResponseEntity<Booking> updateBooking(@RequestBody EditBookingRequest request){
	        return bookingService.update(request);
	    }

	    
	   
	    @GetMapping("/bookings/{id}")
	    public ResponseEntity<Booking> getBookingById(@PathVariable("id") String id){
	        return bookingService.getById(id);
	    }
	    
	    @GetMapping("/bookings/u/{id}")
	    public ResponseEntity<List<EventBookedResponse>> getBookingByIdUser(@PathVariable("id") String id){
	        return bookingService.getByIdUser(id);
	    }
	    

	    @GetMapping("/bookings")
	    public ResponseEntity<List<Booking>> getAllBookings(){
	        return bookingService.getAll();
	    }

	   
	    @DeleteMapping("/bookings/{id}")
	    public boolean deleteBookingById(@PathVariable("id") String id){
	        return bookingService.delete(id);
	    }
	    
	    @PostMapping("/bookings/check")
	    public ResponseEntity<String> getCheckBooking(@RequestBody CheckBookingRequest request){
	        return bookingService.getCheck(request.bookingNr,request.eventId);
	    }

}
