package com.scarcolo.eventour.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public ResponseEntity<User> add(AddUserRequest request) throws Exception {
    	User user = userRepository.save(new User(request));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

  
    public ResponseEntity<User> update(EditUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<User> getById(String id) {
    	Optional<User> userData = userRepository.findById(id);

  	  if (userData.isPresent()) {
  	    return new ResponseEntity<>(userData.get(), HttpStatus.OK);
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

	public ResponseEntity<List<User>> getAll() {
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
