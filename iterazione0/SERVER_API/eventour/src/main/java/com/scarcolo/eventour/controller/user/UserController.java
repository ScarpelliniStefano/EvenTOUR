
package com.scarcolo.eventour.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.AccountRequest;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.service.user.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	    /**
    	 * Gets the account.
    	 *
    	 * @param request the request
    	 * @return the account
    	 */
    	@PostMapping("/account")
	    public ResponseEntity<AccountResponse> getAccount(@RequestBody AccountRequest request){
	        return userService.getAccount(request.username,request.password);
	    }

}
