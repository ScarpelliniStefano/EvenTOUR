package com.scarcolo.eventour.service.user;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.manager.RequestRepository;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;
import com.scarcolo.eventour.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {

	/** The user repository. */
    @Autowired
    private UserRepository userRepository;
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	/** The manager repository. */
	@Autowired
	private RequestRepository requestRepository;
	
	/** The ticket insp repository. */
	@Autowired
	private TicketInspRepository ticketInspRepository;
	
	/** The event repository. */
	@Autowired
	private EventRepository eventRepository;

   
    /**
     * Add a user.
     *
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity<UserResponse> add(AddUserRequest request) throws Exception{
    	User user = userRepository.save(new User(request));
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

  
    /**
     * Update a user.
     *
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity<UserResponse> update(EditUserRequest request) throws Exception {
    	System.out.println(request.residence.getCity());
        Optional<User> optionalUser = userRepository.findById(request.id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User u=optionalUser.get();
        if(request.residence!=null) {
        	u.setResidence(request.residence);
        }
        if(request.types!=null) {
        	u.setTypes(request.types);
        }
        userRepository.save(u);
        return new ResponseEntity<>(new UserResponse(u), HttpStatus.OK);
    }

   
    /**
     * Gets the user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public ResponseEntity<UserResponse> getById(String id){
    	Optional<User> userData = userRepository.findById(id);

  	  if (userData.isPresent()) {
  	    return new ResponseEntity<>(new UserResponse(userData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    /**
     * Delete a user.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return false;
        }
        userRepository.deleteById(optionalUser.get().getId());
        return true;
    }

	/**
	 * Gets all users.
	 *
	 * @return all users
	 */
	public ResponseEntity<List<UserResponse>> getAll() {
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<UserResponse> userR= new ArrayList<>();
			for(User user: users) userR.add(new UserResponse(user));
			return new ResponseEntity<>(userR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets the account of a given mail/code and password.
	 *
	 * @param user the user
	 * @param psw the psw
	 * @return the account
	 */
	public ResponseEntity<AccountResponse> getAccount(String user, String psw) {
		
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Object objResp;
		try {
			if(!user.contains("@")) {
				List<TicketInsp> tickets = ticketInspRepository.findByCode(user);
				if(tickets.size()==0) {
					return new ResponseEntity<>(new AccountResponse("NONE","ERROR. unregistered ticket inspector"),HttpStatus.OK);
				}else {
					for(TicketInsp ticket : tickets) {
						
						if(Functionalities.getMd5(ticket.getPassword()).equals(psw)) {
							objResp=new TicketInspResponse(ticket);
							return new ResponseEntity<>(new AccountResponse("TicketInsp",objResp),HttpStatus.OK);
						}
					}
					return new ResponseEntity<>(new AccountResponse("TicketInsp","ERROR. invalid password"),HttpStatus.OK);
				}
			}else {
				List<User> users = userRepository.findByMail(user);
				List<Manager> managers = managerRepository.findByMail(user);
				if(users.isEmpty()&&managers.isEmpty()) {
					return new ResponseEntity<>(new AccountResponse("NONE","ERROR. unregistered user"),HttpStatus.OK);
				}else {
					if(!users.isEmpty()) {
						if(Functionalities.getMd5(users.get(0).getPassword()).equals(psw)) {
							objResp=new UserResponse(users.get(0));
							return new ResponseEntity<>(new AccountResponse("User",objResp),HttpStatus.OK);
						}else {
							return new ResponseEntity<>(new AccountResponse("User","ERROR. invalid password"),HttpStatus.OK);
						}
					}else {
						if(Functionalities.getMd5(managers.get(0).getPassword()).equals(psw)) {
							Request req= requestRepository.findByManagerId(managers.get(0).getId()).get(0);
							if(!req.isActive()) {
								return new ResponseEntity<>(new AccountResponse("Manager","ERROR. no active manager"),HttpStatus.OK);
							}
							LocalDate dateCheck=Functionalities.convertToLocalDate(req.getDateRenewal()).plusYears(1);
							if(dateCheck.isAfter(LocalDate.now())) {
								objResp=new ManagerPlusResponse(managers.get(0),req);
								return new ResponseEntity<>(new AccountResponse("Manager",objResp),HttpStatus.OK);
							}else {
								return new ResponseEntity<>(new AccountResponse("Manager","ERROR. renewal date is passed"),HttpStatus.OK);
							}
							
						}else {
							return new ResponseEntity<>(new AccountResponse("Manager","ERROR. invalid password"),HttpStatus.OK);
						}
					}
				}
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	/**
	 * Gets all booking by id user
	 * @param id the id user
	 * @return bookings by id user
	 */
	private List<EventBookedResponse> getByIdUser(String id) {
		try {
			List<EventBookedResponse> eventR=bookingRepository.findByUserId(new ObjectId(id));
			return eventR;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Gets the even tour.
	 *
	 * @param userId the user id
	 * @param n the number of events wanted
	 * @return the even tour
	 */
	public ResponseEntity<List<EventResponse>> getEvenTour(String userId,Integer n) {
		Optional<User> userData = userRepository.findById(userId);
		User u=null;
	  	if (userData.isPresent()) {
	  	    u=userData.get();
	  	} else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	}
	  	List<EventBookedResponse> bookingData = this.getByIdUser(userId);
		//1.filtra per regione
		//2.ordinamento dataOra
		List<Event> eventR=eventRepository.findByLocation_RegioneLikeAndFreeSeatGreaterThanZero(u.getResidence().getRegione() ,Sort.by("dataOra").ascending());
		//3.ciclo 
		List<Event> s=new ArrayList<>();
		List<Event> sSub=new ArrayList<>();
		Integer c=0;
		Integer cSub=0;
		Boolean sel=false;
		Boolean selSub=false;
		if(bookingData!=null) {
			for(int j=0;j<bookingData.size();j++) {
				eventR.remove(bookingData.get(j).getEvent()[0]);
				LocalDate dateE=bookingData.get(j).getEvent()[0].getDataOra().toLocalDate();
				eventR.removeIf(e -> dateE.isEqual(e.getDataOra().toLocalDate()));
			}
		}
		for(int i=0;i<eventR.size();i++) {
			if(c==n) {
				List<EventResponse> eventResponse=new ArrayList<>();
				for(Event e : s) {
					eventResponse.add(new EventResponse(e));
				}
				return new ResponseEntity<>(eventResponse,HttpStatus.OK);
			}
			sel=false;
			selSub=false;
			for(int j=0;!sel && j<eventR.get(i).getTypes().length;j++) {
				for(String k: u.getTypes()) {
						if(!sel && (s.isEmpty() || !s.get(s.size()-1).getDataOra().toLocalDate().isEqual(eventR.get(i).getDataOra().toLocalDate()))) {
							//controllo data
							if(eventR.get(i).getTypes()[j].toString().equalsIgnoreCase(k.toString())) {
								if(!sSub.isEmpty() && sSub.get(sSub.size()-1).getId().equalsIgnoreCase(eventR.get(i).getId()))
									sSub.remove(sSub.size()-1);
								s.add(eventR.get(i));
								sel=true;
								c++;
							}else if(!selSub && cSub<n && Functionalities.similType(eventR.get(i).getTypes()[j],k)){
								sSub.add(eventR.get(i));
								selSub=true;
								cSub++;
							}
						}
				}
			}
			
		}
		
		for(int h=0;h<(n-s.size());h++) {
			s.add(sSub.get(h));
		}
		
		s=Functionalities.orderByData(s);
		
		List<EventResponse> eventResponse=new ArrayList<>();
		for(Event e : s) {
			eventResponse.add(new EventResponse(e));
		}
		return new ResponseEntity<>(eventResponse,HttpStatus.OK);
	/*
		for(int i=0;i<eventR.size();i++) {
			if(c==n) {
				List<EventResponse> eventResponse=new ArrayList<>();
				for(Event e : s) {
					eventResponse.add(new EventResponse(e));
				}
				return new ResponseEntity<>(eventResponse,HttpStatus.OK);
			}
			if(!s.contains(eventR.get(i))) {
				sel=false;
				for(int j=0;!sel && j<eventR.get(i).getTypes().length;j++) {
					for(String k: u.getTypes()) {
						if(!sel) {
							//controllo data 
							if(s.isEmpty() || !s.get(s.size()-1).getDataOra().toLocalDate().isEqual(eventR.get(i).getDataOra().toLocalDate())) {
								if(Functionalities.similType(eventR.get(i).getTypes()[j],k)) {
									s.add(eventR.get(i));
									sel=true;
									c++;
								}
							}
						}
					}
				}
			}
		}
		List<EventResponse> eventResponse=new ArrayList<>();
		for(Event e : s) {
			eventResponse.add(new EventResponse(e));
		}
		return new ResponseEntity<>(eventResponse,HttpStatus.OK);
		*/
		
		
	}
	
	
}
