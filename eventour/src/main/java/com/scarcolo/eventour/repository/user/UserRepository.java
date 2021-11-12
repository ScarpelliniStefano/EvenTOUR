package com.scarcolo.eventour.repository.user;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.user.User;






public interface UserRepository extends MongoRepository<User, String> {
	
	
	
}
