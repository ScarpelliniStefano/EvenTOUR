package com.scarcolo.eventour.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;
import com.scarcolo.eventour.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public ResponseEntity<UserResponse> add(AddUserRequest request) throws Exception{
    	User user = userRepository.save(new User(request));
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

  
    public ResponseEntity<UserResponse> update(EditUserRequest request) throws Exception {
        Optional<User> optionalUser = userRepository.findById(request.id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new UserResponse(optionalUser.get()), HttpStatus.OK);
    }

   
    public ResponseEntity<UserResponse> getById(String id){
    	Optional<User> userData = userRepository.findById(id);

  	  if (userData.isPresent()) {
  	    return new ResponseEntity<>(new UserResponse(userData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    public boolean delete(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return false;
        }
        userRepository.deleteById(optionalUser.get().getId());
        return true;
    }

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

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private TicketInspRepository ticketInspRepository;
	
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
						if(ticket.getPassword().equals(psw)) {
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
						if(users.get(0).getPassword().equals(psw)) {
							objResp=new UserResponse(users.get(0));
							return new ResponseEntity<>(new AccountResponse("User",objResp),HttpStatus.OK);
						}else {
							return new ResponseEntity<>(new AccountResponse("User","ERROR. invalid password"),HttpStatus.OK);
						}
					}else {
						if(managers.get(0).getPassword().equals(psw)) {
							objResp=new ManagerResponse(managers.get(0));
							return new ResponseEntity<>(new AccountResponse("Manager",objResp),HttpStatus.OK);
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
	private EventRepository eventRepository;
	public ResponseEntity<List<EventResponse>> getEvenTour(String userId,Integer n) {
		Optional<User> userData = userRepository.findById(userId);
		User u=null;
	  	if (userData.isPresent()) {
	  	    u=userData.get();
	  	} else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	}
		//1.filtra per regione
		//2.ordinamento dataOra
		List<Event> eventR=eventRepository.findByLocation_RegioneLikeAndFreeSeatGreaterThanZero(u.getResidence().getRegione() ,Sort.by("dataOra").ascending());
		//3.ciclo 
		List<Event> s=new ArrayList<>();
		Integer c=0;
		Boolean sel=false;
		for(int i=0;i<eventR.size();i++) {
			if(c==n) {
				List<EventResponse> eventResponse=new ArrayList<>();
				for(Event e : s) {
					eventResponse.add(new EventResponse(e));
				}
				return new ResponseEntity<>(eventResponse,HttpStatus.OK);
			}
			sel=false;
			for(int j=0;!sel && j<eventR.get(i).getTypes().length;j++) {
				for(String k: u.getTypes()) {
					if(!sel) {
						if(eventR.get(i).getTypes()[j].toString().equalsIgnoreCase(k.toString())) {
							s.add(eventR.get(i));
							sel=true;
							c++;
						}
					}
				}
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
			if(!s.contains(eventR.get(i))) {
				sel=false;
				for(int j=0;!sel && j<eventR.get(i).getTypes().length;j++) {
					for(String k: u.getTypes()) {
						if(!sel) {
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
		List<EventResponse> eventResponse=new ArrayList<>();
		for(Event e : s) {
			eventResponse.add(new EventResponse(e));
		}
		return new ResponseEntity<>(eventResponse,HttpStatus.OK);
		
		
		
	}
	
	
}
