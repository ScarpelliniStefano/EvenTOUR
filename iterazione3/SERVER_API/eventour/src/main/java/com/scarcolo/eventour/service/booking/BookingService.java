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
import com.scarcolo.eventour.model.booking.PaymentRequest;
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.event.EventPlus;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserBookedResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.event.EventPlusRepository;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.user.UserRepository;
import com.scarcolo.eventour.service.manager.RequestService;
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
    
    /** The eventplus repository. */
    @Autowired
    private EventPlusRepository eventPlusRepository;
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /** The manager repository. */
    @Autowired
    private ManagerRepository managerRepository;
    
    /** The manager repository. */
    @Autowired
    private RequestService requestService;

   
    /**
     * Adds new booking.
     *
     * @param request the request of new booking
     * @return the response entity
     * @throws IOException exception from inputstream file template
     */
    public ResponseEntity<Object> add(AddBookingRequest request) throws IOException {
        Booking booking = new Booking(request);
        Optional<Event> optionalEvent = eventRepository.findById(booking.getEventId());
        Optional<User> optionalUser = userRepository.findById(booking.getUserId());
        if (!optionalUser.isEmpty() && !optionalEvent.isEmpty()) {
            Event eventM=optionalEvent.get();
            if(eventM.getFreeSeat()-booking.getPrenotedSeat()>=0) {
            	eventM.setFreeSeat(eventM.getFreeSeat()-booking.getPrenotedSeat());
            	eventRepository.save(eventM);
            	boolean result=false;
            	Booking book=bookingRepository.save(booking);
            	try {
            		result = Mail.sendBookingEventMsg(optionalUser.get().getEmail(),eventM,book);
            	} catch (IOException e) {
            		throw e;
            	}
            	if(!result) {
            		//System.out.println("error in sending mail");
            	}else {
            		return new ResponseEntity<>(book, HttpStatus.OK);
            	}
            }else{
            	return new ResponseEntity<>("ERROR. no enough seats available.", HttpStatus.NO_CONTENT);
            }
         } else {
     		 return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
         }
            	
        return new ResponseEntity<>(booking, HttpStatus.OK);
        
    }

    /**
     * Modify if user comes.
     *
     * @param id the id
     */
    private void modifyComed(String id) {
    	Optional<Booking> optionalBooking = bookingRepository.findById(id);
    	if (!optionalBooking.isEmpty()) {
            Booking book=optionalBooking.get();
            book.setCome(true);
            bookingRepository.save(book);
    	}
    }
    
    
    
    /**
     * Modify the review of a booking.
     *
     * @param id the id of booking
     * @param review the review
     */
    private void modifyReview(String id, int review) {
    	Optional<Booking> optionalBooking = bookingRepository.findById(id);
    	if (!optionalBooking.isEmpty()) {
            Booking book=optionalBooking.get();
            EventPlus ev=eventPlusRepository.findById(book.getEventId()).get();
            if(book.getReview()>0) {
            	ev.setReviewSum(ev.getReviewSum()-book.getReview());
            	ev.setReviewTot(ev.getReviewTot()-1);
            }
            book.setReview(review);
            ev.setReviewSum(ev.getReviewSum()+book.getReview());
            ev.setReviewTot(ev.getReviewTot()+1);
            eventPlusRepository.save(ev);
            bookingRepository.save(book);
    	}
    }

   
    /**
     * Gets one booking by id.
     *
     * @param id the id of booking
     * @return the booking by id
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
     * @param id the id of booking to remove
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Gets all booking by id user.
	 *
	 * @param page the page
	 * @param size the size
	 * @param id the id user
	 * @param pastFuture the past future character for show only future event's booking  or past event's booking
	 * @return bookings by id user
	 */
	public ResponseEntity<List<EventBookedResponse>> getByIdUser(int page, int size, String id, char pastFuture) {
		try {
			Optional<User> userOpt=userRepository.findById(id);
			if(userOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			AggregationResults<EventBookedResponse> eventsA=null;
			if(pastFuture=='f' || pastFuture=='F')
				eventsA=bookingRepository.findByUserIdFuture(new ObjectId(id),page*size,size);
			else if(pastFuture=='p' || pastFuture=='P')
				eventsA=bookingRepository.findByUserIdPast(new ObjectId(id),page*size,size);
			else
				eventsA=bookingRepository.findByUserId(new ObjectId(id),page*size,size);
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/**
	 * Gets booking by user and event.
	 *
	 * @param id the id of user
	 * @param idEv the id event
	 * @return the booking by user and event
	 */
	public ResponseEntity<List<EventBookedResponse>> getByUserAndEvent(String id, String idEv) {
		try {
			AggregationResults<EventBookedResponse> eventsA=bookingRepository.findByUserAndEvent(id, idEv);
			List<EventBookedResponse> eventR=eventsA.getMappedResults();
			if(eventR.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	 * @return if booking is correct
	 */
	public ResponseEntity<String> getCheck(String idBooking,String idEvent) {
    	Optional<Booking> bookingData = bookingRepository.findById(idBooking);
    	if (bookingData.isPresent()) {
  	  		if(bookingData.get().getEventId().equalsIgnoreCase(idEvent)) {
  	  			modifyComed(idBooking);
  	  			return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
  	  		}else
  	  			return new ResponseEntity<>("ACCESS REFUSED", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>("INVALID BOOKING CODE",HttpStatus.OK);
  	  	}
    }

	/**
	 * Gets the event's booking by id event.
	 *
	 * @param id the id of event
	 * @return the booking by id event
	 */
	public ResponseEntity<List<UserBookedResponse>> getByIdEvent(String id) {
		try {
			AggregationResults<UserBookedResponse> userA=bookingRepository.findByEventId(new ObjectId(id));
			List<UserBookedResponse> eventR=userA.getMappedResults();
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	/**
	 * Get a booking by id, with details.
	 *
	 * @param id the id of booking
	 * @return the booking by id, with details
	 */
	public ResponseEntity<UserEventBookedResponse> getByIdDetails(String id) {
		try {
			AggregationResults<UserEventBookedResponse> userEventA=bookingRepository.findByIdDetails(new ObjectId(id));
			List<UserEventBookedResponse> eventR=userEventA.getMappedResults();
			if(!eventR.isEmpty())
				return new ResponseEntity<>(eventR.get(0), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			//System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
    
    //CARDNR: tutte quelle che iniziano con 400000380000 son accettate, previo superamento check vari
    //CVV: almeno un 2 deve esserci
	//data come "MM/AA"
    /**
     * Payment method.
     *
     * @param type the type of user
     * @param request the request of payment (date must be in MM/AA format)
     * @return the response entity with outcome of payment
     */
    
	public ResponseEntity<String> checkerPayment(String type, PaymentRequest request) {
		LocalDate dt=LocalDate.of(Integer.parseInt("20"+request.dateScad.split("/")[1]), Integer.parseInt(request.dateScad.split("/")[0]),01);
		request.cardNr=request.cardNr.replaceAll(" ", "").replaceAll("-", "");
		if(dt.isBefore(LocalDate.now())) {
        	return new ResponseEntity<>("INVALID DATE",HttpStatus.OK);
        }else if(request.amount.compareTo(0d)<=0) {
        	return new ResponseEntity<>("AMOUNT INVALID",HttpStatus.OK);
        }else if(request.authNr.length()!=3 || request.cardNr.length()!=16) {
        	return new ResponseEntity<>("CARD NUMBER OR CVV INVALID",HttpStatus.OK);
        }else {
        	Manager man=null;
        	if(type.toUpperCase().contentEquals("USER")) {
        		Optional<User> optionalUser = userRepository.findById(request.idUser);
        		if (optionalUser.isEmpty()) {
        			return null;
        		}
        		//User user=optionalUser.get(); //in real payment will serve
        	}else if(type.toUpperCase().contentEquals("MANAGER")){
        		if(!request.idUser.matches("0000")) {
        			Optional<Manager> optionalMan = managerRepository.findById(request.idUser);
        			if (optionalMan.isEmpty()) {
        				return null;
        			}
        			man=optionalMan.get();
        		}
        		
        	}
            PaymentService ps = new PaymentServiceImpl();
            //METHOD FOR INSERT A NEW CUSTOMER
        	/*Customer cust=ps.createCustomer("payment for booking", user.getName(),user.getSurname(), user.getEmail());
        	  System.out.println(cust);*/
        	Order order=new Order();
        	//METHOD FOR CONFIGURING A NEW ORDER
        	/*order.setCurrency("EUR");
        	  order.setCustomerObject(cust);
        	  order.setAmount(request.amount.longValue());*/
        	String cs = "";
			try {
				cs = ps.chargeCreditCard(order,request.cardNr,String.valueOf(dt.getMonthValue()),String.valueOf(dt.getYear()),request.authNr);
				if(cs=="cb5e100e5a9a3e7f6d1fd97512215282") {
					return new ResponseEntity<>("ERROR with payment. Transaction: "+cs,HttpStatus.OK);
				}
			} catch (NoSuchAlgorithmException e) {
				return new ResponseEntity<>("ERROR",HttpStatus.NO_CONTENT);
			}
			if(man!=null) {
				requestService.updateRenewal(man.getId());
			}
        	return new ResponseEntity<>("OK. Transaction: "+cs,HttpStatus.OK);
        }
	}

	
	//access to mail:
	//username: eventourcs@gmail.com
	//psw: #eventour96
	
	//for checking mail sended:
	//access to mailtrap with google account (credential written up here)
	/**
	 * Delete all booking from event, with notice to all user booked for event.
	 *
	 * @param event the event to delete
	 * @return true, if successful
	 * @throws IOException exception from inputstream file template
	 */
	public boolean deleteAllBookingFromEvent(Event event) throws IOException {
		ResponseEntity<List<UserBookedResponse>> bookings=this.getByIdEvent(event.getId());
		if(bookings.getStatusCode().is2xxSuccessful()) {
			for(UserBookedResponse book : bookings.getBody()) {
				boolean result=false;
				try {
					result = Mail.sendDeleteEventMsg(book.getUser()[0].getEmail(),event);
				} catch (IOException e) {
					throw e;
				}
				if(!result) {
					//System.out.println("error in sending mail");
				}
				this.delete(book.getId());
			}
			return true;
		}
		return false;
		
	}

	/**
	 * Sets the review of a booking.
	 *
	 * @param idBooking the id of booking
	 * @param review the review
	 * @return the response entity with outcome
	 */
	public ResponseEntity<String> setReview(String idBooking, Integer review) {
		Optional<Booking> bookingData = bookingRepository.findById(idBooking);
  	  	if (bookingData.isPresent()) {
  	  		if(!bookingData.get().getCome()) {
  	  			return new ResponseEntity<>("INVALID CODE BOOKING", HttpStatus.OK);
  	  		}
  	  		if(review>0 && review<6) {
  	  			modifyReview(idBooking,review);
  	  			return new ResponseEntity<>("MODIFIED", HttpStatus.OK);
  	  		}else
  	  			return new ResponseEntity<>("INVALID REVIEW", HttpStatus.OK);
  	  	} else {
  	  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  	}
	}
}