package com.scarcolo.eventour.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.ticketisp.TicketInspRepository;
import com.scarcolo.eventour.repository.user.UserRepository;

import java.util.List;


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
	
	/** The ticket insp repository. */
	@Autowired
	private TicketInspRepository ticketInspRepository;
	
	/**
	 * Get account, given mail or code and password.
	 *
	 * @param user the username (mail or code)
	 * @param psw the password
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

	
	
}
