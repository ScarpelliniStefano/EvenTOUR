package com.scarcolo.eventour.service.booking;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Mail;
import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.EditBookingRequest;
import com.scarcolo.eventour.model.booking.PaymentRequest;
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserBookedResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.user.UserRepository;
import com.stripe.model.Order;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
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

   
    /**
     * Adds new booking.
     *
     * @param request the request of new booking
     * @return the response entity
     */
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

    /**
     * Modify if user comes.
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
     * Update one booking.
     *
     * @param request the request
     * @return the response entity
     */
    public ResponseEntity<Booking> update(EditBookingRequest request) {
        Optional<Booking> optionalBooking = bookingRepository.findById(request.id);
        if (optionalBooking.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalBooking.get(), HttpStatus.OK);
    }

   
    /**
     * Gets one booking by id.
     *
     * @param id the id
     * @return the event by id
     */
    public ResponseEntity<Booking> getById(String id) {
    		Optional<Booking> bookingData = bookingRepository.findById(id);

    		if (bookingData.isPresent()) {
    			return new ResponseEntity<>(bookingData.get(), HttpStatus.OK);
    		} else {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	 * Get all bookings.
	 *
	 * @return all bookings
	 */
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


	/**
	 * Gets all booking by id user.
	 * @param size 
	 * @param page 
	 *
	 * @param id the id user
	 * @return bookings by id user
	 */
	public ResponseEntity<List<EventBookedResponse>> getByIdUser(int page, int size, String id) {
		try {
			System.out.println(page+" "+size+" "+id);
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserId(new ObjectId(id),page*size,size);
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/**
	 * Gets booking by user and event.
	 *
	 * @param idUser the id user
	 * @param idEvent the id event
	 * @return the booking by user and event
	 */
	public ResponseEntity<List<EventBookedResponse>> getByUserAndEvent(String id, String idEv) {
		try {
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserAndEvent(new ObjectId(id), new ObjectId(idEv));
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
		System.out.println(idBooking);
    	System.out.println(idEvent);
    	Optional<Booking> bookingData = bookingRepository.findById(idBooking);
    	
    	
  	  	if (bookingData.isPresent()) {
  	  		System.out.println(bookingData.get().getId());
  	  		if(bookingData.get().getEventId().equalsIgnoreCase(idEvent)) {
  	  			modify(idBooking);
  	  			return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
  	  		}else
  	  			return new ResponseEntity<>("ACCESS REFUSED", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>("INVALID BOOKING CODE",HttpStatus.OK);
  	  	}
    }

	/**
	 * Gets the by id event.
	 *
	 * @param id the id
	 * @return the by id event
	 */
	public ResponseEntity<List<UserBookedResponse>> getByIdEvent(String id) {
		try {
			AggregationResults<UserBookedResponse> userA=bookingRepository.findByEventId(new ObjectId(id));
			List<UserBookedResponse> eventR=userA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	/**
	 * Gets the by id details.
	 *
	 * @param id the id
	 * @return the by id details
	 */
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
	
	/** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /** The manager repository. */
    @Autowired
    private ManagerRepository managerRepository;
    //CARDNR: tutte quelle che iniziano con 400000380000 son accettate, previo superamento check vari
    //CVV: almeno un 2 deve esserci
    //data come "MM/AA"
	public ResponseEntity<String> checkerPayment(String type, PaymentRequest request) {
		System.out.println(request.dateScad);
		LocalDate dt=LocalDate.of(Integer.parseInt("20"+request.dateScad.split("/")[1]), Integer.parseInt(request.dateScad.split("/")[0]),01);
		request.cardNr=request.cardNr.replaceAll(" ", "");
		request.cardNr=request.cardNr.replaceAll("-", "");
		if(dt.isBefore(LocalDate.now())) {
        	return new ResponseEntity<>("INVALID DATE",HttpStatus.OK);
        }else if(request.amount.compareTo(0d)<=0) {
        	return new ResponseEntity<>("AMOUNT INVALID",HttpStatus.OK);
        }else if(request.authNr.length()!=3 || request.cardNr.length()!=16) {
        	return new ResponseEntity<>("CARD NUMBER OR CVV INVALID",HttpStatus.OK);
        }else {
        	if(type.toUpperCase()=="USER") {
        		Optional<User> optionalUser = userRepository.findById(request.idUser);
        		if (optionalUser.isEmpty()) {
        			return null;
        		}
        		User user=optionalUser.get();
        		System.out.println(user.getEmail());
        	}else if(type.toUpperCase()=="MANAGER"){
        		Optional<Manager> optionalMan = managerRepository.findById(request.idUser);
        		if (optionalMan.isEmpty()) {
        			return null;
        		}
        		Manager man=optionalMan.get();
        		System.out.println(man.getMail());
        	}
            
            
            PaymentService ps = new PaymentServiceImpl();
        	//Customer cust=ps.createCustomer("payment for booking", user.getName(),user.getSurname(), user.getEmail());
        	//System.out.println(cust);
        	Order order=new Order();
        	/*order.setCurrency("EUR");
        	order.setCustomerObject(cust);
        	order.setAmount(request.amount.longValue());*/
        	String cs;
			try {
				cs = ps.chargeCreditCard(order,request.cardNr,String.valueOf(dt.getMonthValue()),String.valueOf(dt.getYear()),request.authNr);
				if(cs=="cb5e100e5a9a3e7f6d1fd97512215282") {
					return new ResponseEntity<>("ERROR with payment. Transaction: "+cs,HttpStatus.OK);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>("ERROR",HttpStatus.OK);
			}
        	return new ResponseEntity<>("OK. Transaction: "+cs,HttpStatus.OK);
        }
	}

	
	//access to mail:
	//username: eventourcs@gmail.com
	//psw: #eventour96
	
	//for checking mail sended:
	//access to mailtrap with google account (credential written up here)
	public boolean deleteAllBookingFromEvent(Event event) {
		ResponseEntity<List<UserBookedResponse>> bookings=this.getByIdEvent(event.getId());
		if(bookings.getStatusCode().is2xxSuccessful()) {
			for(UserBookedResponse book : bookings.getBody()) {
				boolean result=false;
				try {
					result = Mail.sendDeleteEventMsg(book.getUser()[0].getEmail(),event);
				} catch (IOException e) {
					System.out.println(e);
				}
				if(!result) {
					System.out.println("error in sending mail");
				}
				this.delete(book.getId());
			}
			return true;
		}
		return false;
		// TODO Auto-generated method stub
		
	}
}
