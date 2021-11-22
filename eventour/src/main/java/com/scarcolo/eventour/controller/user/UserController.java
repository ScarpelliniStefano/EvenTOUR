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
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;
import com.scarcolo.eventour.service.user.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	 
	@PostMapping("/users")
	public ResponseEntity<UserResponse> addUser(@RequestBody AddUserRequest request) throws Exception{
	      return userService.add(request);
	}

	   
	    @PutMapping("/users")
	    public ResponseEntity<UserResponse> updateUser(@RequestBody EditUserRequest request) throws Exception{
	        return userService.update(request);
	    }


	   
	    @GetMapping("/users/{id}")
	    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id){
	        return userService.getById(id);
	    }
	    

	    @GetMapping("/users")
	    public ResponseEntity<List<UserResponse>> getAllUsers(){
	        return userService.getAll();
	    }

	   
	    @DeleteMapping("/users/{id}")
	    public boolean deleteUserById(@PathVariable("id") String id){
	        return userService.delete(id);
	    }

}
