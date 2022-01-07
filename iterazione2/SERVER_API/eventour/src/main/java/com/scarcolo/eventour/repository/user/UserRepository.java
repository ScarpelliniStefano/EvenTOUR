package com.scarcolo.eventour.repository.user;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.user.User;






// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * Find by mail.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<User> findByMail(String user);
	
	
	
}
