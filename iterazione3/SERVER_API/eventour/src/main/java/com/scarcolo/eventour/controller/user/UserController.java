package com.scarcolo.eventour.controller.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.AccountRequest;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.event.EventResponseTourComplete;
import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.UserResponse;
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
	 * Adds the user.
	 *
	 * @param request the request of new user
	 * @return the response entity with created user
	 */
	@PostMapping("/users")
	public ResponseEntity<UserResponse> addUser(@RequestBody AddUserRequest request){
	      return userService.add(request);
	}

	   
    /**
	 * Update user.
	 *
	 * @param request the request of update
	 * @return the response entity with updated user
	 */
	@PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@RequestBody EditUserRequest request){
        return userService.update(request);
    }
   
    /**
	 * Gets the user by id.
	 *
	 * @param id the id of user
	 * @return the user by id
	 */
	@GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id){
        return userService.getById(id);
    }
    

    /**
	 * Gets all users.
	 *
	 * @return all users
	 */
	@GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return userService.getAll();
    }
    
    /**
     * Gets the even tour.
     *
     * @param id the id
     * @param num the num
     * @param numPersone the num of people for witch doing the eventour
     * @return the even tour
     */
	@GetMapping("/users/{id}/eventour/{num}")
    public ResponseEntity<EventResponseTourComplete> getEvenTour(@PathVariable("id") String id,@PathVariable("num") int num, @RequestParam(defaultValue = "1") int numPersone){
        return userService.getEvenTour(id, num, numPersone);
    }
    
    /**
	 * Gets the account.
	 *
	 * @param request the request with username/mail and password
	 * @return the account
	 */
	@PostMapping("/account")
    public ResponseEntity<AccountResponse> getAccount(@RequestBody AccountRequest request){
        return userService.getAccount(request.username,request.password);
    }
	
	/**
	 * change password of a account.
	 *
	 * @param request the request of change
	 * @return the account
	 */
	@PostMapping("/account/changePsw")
    public ResponseEntity<AccountResponse> setAccountPsw(@RequestBody AccountRequest request){
        return userService.setAccountPsw(request.username,request.password);
    }

   
    /**
	 * Delete user by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") String id){
        return userService.delete(id);
    }

}