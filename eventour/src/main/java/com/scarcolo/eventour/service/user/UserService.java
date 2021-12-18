package com.scarcolo.eventour.service.user;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.Mail;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.admin.Admin;
import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.event.EventResponseTour;
import com.scarcolo.eventour.model.event.EventResponseTourComplete;
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
import com.scarcolo.eventour.repository.admin.AdminRepository;
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
	private AdminRepository adminRepository;
	
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
					return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid password"),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
				}
			}else {
				List<User> users = userRepository.findByMail(user);
				List<Manager> managers = managerRepository.findByMail(user);
				if(users.isEmpty()&&managers.isEmpty()) {
					List<Admin> admin=adminRepository.findByMail(user);
					if(admin.isEmpty())
						return new ResponseEntity<>(new AccountResponse("NONE","ERROR. unregistered user"),HttpStatus.OK);
					else
						if(Functionalities.getMd5(admin.get(0).getPassword()).equals(psw)) {
							objResp=new AdminResponse(admin.get(0));
							return new ResponseEntity<>(new AccountResponse("Admin",objResp),HttpStatus.OK);
						}else {
							return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid password"),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
						}
				}else {
					if(!users.isEmpty()) {
						if(Functionalities.getMd5(users.get(0).getPassword()).equals(psw)) {
							objResp=new UserResponse(users.get(0));
							return new ResponseEntity<>(new AccountResponse("User",objResp),HttpStatus.OK);
						}else {
							return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid password"),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
						}
					}else {
						if(Functionalities.getMd5(managers.get(0).getPassword()).equals(psw)) {
							Request req= requestRepository.findByManagerId(managers.get(0).getId()).get(0);
							if(!req.isActive()) {
								return new ResponseEntity<>(new AccountResponse("NONE","ERROR. no active manager"),HttpStatus.OK);
							}
							LocalDate dateCheck=Functionalities.convertToLocalDate(req.getDateRenewal()).plusYears(1);
							if(dateCheck.isAfter(LocalDate.now())) {
								objResp=new ManagerPlusResponse(managers.get(0),req);
								return new ResponseEntity<>(new AccountResponse("Manager",objResp),HttpStatus.OK);
							}else {
								return new ResponseEntity<>(new AccountResponse("NONE","ERROR. renewal date is passed"),HttpStatus.OK);
							}
							
						}else {
							return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid password"),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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
	public ResponseEntity<EventResponseTourComplete> getEvenTour(String userId,Integer n, Integer numPers) {
		Optional<User> userData = userRepository.findById(userId);
		User u=null;
	  	if (userData.isPresent()) {
	  	    u=userData.get();
	  	} else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	}
	  	List<EventBookedResponse> bookingData = this.getByIdUser(userId);
		//2.ordinamento dataOra
		List<Event> eventR=eventRepository.findByfreeSeatGreaterThanNumPersone(numPers,Sort.by("dataOra").ascending());
		//3.ciclo 
		List<Event> s=new ArrayList<>();
		if(bookingData!=null) {
			for(int j=0;j<bookingData.size();j++) {
				eventR.remove(bookingData.get(j).getEvent()[0]);
				LocalDate dateE=bookingData.get(j).getEvent()[0].getDataOra().toLocalDate();
				eventR.removeIf(e -> dateE.isEqual(e.getDataOra().toLocalDate()));
			}
		}
		
		final Double latU=u.getResidence().getLat();
		final Double lngU=u.getResidence().getLng();
		
		List<Event> eventCopy=eventR;
		eventCopy.removeIf(event -> (event.getLocation().getLat()==null || event.getLocation().getLng()==null || Functionalities.distance(latU, lngU, event.getLocation().getLat(), event.getLocation().getLng())>50));
		
		Event eventChoice=null;
		Double peso=Double.MAX_VALUE;
		for(int i=0;i<eventCopy.size();i++) {

			for(int j=0;j<eventCopy.get(i).getTypes().length;j++) {
				for(String k: u.getTypes()) {
						if((s.isEmpty() || !s.get(s.size()-1).getDataOra().toLocalDate().isEqual(eventCopy.get(i).getDataOra().toLocalDate()))) {
							//controllo data
							
							if(eventCopy.get(i).getTypes()[j].toString().equalsIgnoreCase(k.toString())) {
								if(peso>(1*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(latU, lngU, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng())))) {
									eventChoice=eventCopy.get(i);
									peso=0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(latU, lngU, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng());
								}
							}else if(Functionalities.similType(eventCopy.get(i).getTypes()[j],k)){
								if(peso>(2*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(latU, lngU, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng())))) {
									eventChoice=eventCopy.get(i);
									peso=2*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(latU, lngU, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng()));
								}
							}
						}
				}
			}
		}
		if(eventChoice==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		s.add(eventChoice);
		eventCopy=eventR;
		boolean choiceNull=false;
		while(s.size()<=n && !choiceNull) {
			eventCopy=eventR;
			final Double lanRef=s.get(s.size()-1).getLocation().getLat();
			final Double lngRef=s.get(s.size()-1).getLocation().getLng();
			eventCopy.removeIf(event -> (event.getLocation().getLat()==null || event.getLocation().getLng()==null || 
											Functionalities.distance(lanRef, lngRef, event.getLocation().getLat(), event.getLocation().getLng())>50 ||
											event.getDataOra().isBefore(s.get(s.size()-1).getDataOra()) || event.getDataOra().isAfter(s.get(s.size()-1).getDataOra())));
			eventChoice=null;
			peso=Double.MAX_VALUE;
			for(int i=0;i<eventCopy.size();i++) {
				for(int j=0;j<eventCopy.get(i).getTypes().length;j++) {
					for(String k: u.getTypes()) {
							if((s.isEmpty() || !s.get(s.size()-1).getDataOra().toLocalDate().isEqual(eventCopy.get(i).getDataOra().toLocalDate()))) {
								//controllo data
								if(eventCopy.get(i).getTypes()[j].toString().equalsIgnoreCase(k.toString())) {
									if(peso>(1*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(lanRef, lngRef, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng())))) {
										eventChoice=eventCopy.get(i);
										peso=0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(lanRef, lngRef, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng());
									}
								}else if(Functionalities.similType(eventCopy.get(i).getTypes()[j],k)){
									if(peso>(2*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(lanRef, lngRef, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng())))) {
										eventChoice=eventCopy.get(i);
										peso=2*(0.5*eventCopy.get(i).getPrice()+0.5*Functionalities.distance(lanRef, lngRef, eventCopy.get(i).getLocation().getLat(), eventCopy.get(i).getLocation().getLng()));
									}
								}
							}
					}
				}
			}
			if(eventChoice!=null)
				s.add(eventChoice);
			else
				choiceNull=true;
			
		}
		
		List<EventResponseTour> eventResponse=new ArrayList<>();
		Double distTot=0.0d;
		Double priceTot=0.0d;
		for(int i=0;i<s.size();i++) {
			EventResponseTour et=new EventResponseTour(s.get(i));
			if(i==0)
				distTot+=Functionalities.distance(latU, lngU, s.get(i).getLocation().getLat(), s.get(i).getLocation().getLng());
			else
				distTot+=Functionalities.distance(s.get(i-1).getLocation().getLat(), s.get(i-1).getLocation().getLng(), s.get(i).getLocation().getLat(), s.get(i).getLocation().getLng());
			et.setDistance(distTot);
			et.setPriceTot(et.getPrice()*numPers);
			priceTot+=et.getPriceTot();
			eventResponse.add(et);
		}
		distTot=distTot+Functionalities.distance(s.get(s.size()-1).getLocation().getLat(), s.get(s.size()-1).getLocation().getLng(), latU, lngU);
		EventResponseTourComplete etc=new EventResponseTourComplete(eventResponse,new UserResponse(u), n, eventResponse.size(), priceTot, distTot );
		return new ResponseEntity<>(etc,HttpStatus.OK);
	
		
		
	}


	public ResponseEntity<AccountResponse> setAccountPsw(String user, String psw) {
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Object objResp;
		try {
			if(user.contains("@")) {
				List<Manager> managers = managerRepository.findByMail(user);
				if(managers.isEmpty()) {
						List<User> users = userRepository.findByMail(user);
						if(user.isEmpty()) 
							return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid mail"),HttpStatus.OK);
						users.get(0).setPassword(psw);
						userRepository.save(users.get(0));
						return new ResponseEntity<>(new AccountResponse("User",users.get(0)),HttpStatus.OK);
				}else {
						Request req= requestRepository.findByManagerId(managers.get(0).getId()).get(0);
						if(!req.isActive()) {
							return new ResponseEntity<>(new AccountResponse("Manager","ERROR. no active manager"),HttpStatus.OK);
						}
						LocalDate dateCheck=Functionalities.convertToLocalDate(req.getDateRenewal()).plusYears(1);
						if(dateCheck.isAfter(LocalDate.now())) {
							managers.get(0).setPassword(psw);
							managerRepository.save(managers.get(0));
							objResp=new ManagerPlusResponse(managers.get(0),req);
							return new ResponseEntity<>(new AccountResponse("Manager",objResp),HttpStatus.OK);
						}else {
							return new ResponseEntity<>(new AccountResponse("Manager","ERROR. renewal date is passed"),HttpStatus.OK);
						}		
				}
			}else {
				return new ResponseEntity<>(new AccountResponse("NONE","ERROR. invalid mail"),HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public Integer sendNews() {
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			if(users.isEmpty()) {
				return null;
			}
			int inviate=0;
			for(User user: users) {
				if(user.getNewsletter()) {
					Page<Event> pageEvents = eventRepository.findByTypesAndLocation_RegioneLike(user.getTypes(), user.getResidence().getRegione(), PageRequest.of(0, 5,Sort.by("dataOra").ascending()));
					Mail.sendNewsletterMsg(user.getEmail(),pageEvents.getContent());
					inviate++;
				}
			}
			return inviate;
		}catch(Exception e) {
			return null;
		}
	}
	
	
}
