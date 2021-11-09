package controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.user.User;
import repository.user.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
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
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
	  Optional<User> userData = userRepository.findById(id);

	  if (userData.isPresent()) {
	    return new ResponseEntity<>(userData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	
}
