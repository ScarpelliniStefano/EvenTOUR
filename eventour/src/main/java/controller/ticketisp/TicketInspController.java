package controller.ticketisp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.event.Event;
import model.ticketisp.TicketInsp;
import repository.event.EventRepository;
import repository.ticketisp.TicketInspRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TicketInspController {
	
	@Autowired
	TicketInspRepository ticketinspRepository;
	
	@GetMapping("/ticketinsps")
	public ResponseEntity<List<TicketInsp>> getAllTicketInsp(){
		try {
			List<TicketInsp> ticketinsps = new ArrayList<>();
			ticketinspRepository.findAll().forEach(ticketinsps::add);
			if(ticketinsps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ticketinsps, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getEventById(@PathVariable("id") String id) {
	  Optional<Booking> bookingData = BookingRepository.findById(id);

	  if (bookingData.isPresent()) {
	    return new ResponseEntity<>(bookingData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}*/
}
