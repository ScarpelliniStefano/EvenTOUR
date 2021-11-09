package controller.manager;

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

import model.booking.Booking;
import model.event.Event;
import model.manager.Manager;
import repository.booking.BookingRepository;
import repository.event.EventRepository;
import repository.manager.ManagerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ManagerController {
	
	@Autowired
	ManagerRepository managerRepository;
	
	@GetMapping("/managers")
	public ResponseEntity<List<Manager>> getAllEvents(){
		try {
			List<Manager> managers = new ArrayList<>();
			managerRepository.findAll().forEach(managers::add);
			if(managers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(managers, HttpStatus.OK);
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
