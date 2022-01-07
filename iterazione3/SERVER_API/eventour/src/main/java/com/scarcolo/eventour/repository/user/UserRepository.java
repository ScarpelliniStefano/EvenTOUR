package com.scarcolo.eventour.repository.user;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.user.User;






// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * Find by mail.
	 *
	 * @param user the mail of user
	 * @return the list
	 */
	List<User> findByMail(String user);

	/**
	 * Find by username.
	 *
	 * @param user the username
	 * @return the list
	 */
	@Query("{username:'?0'}")
	List<User> findbyUsername(String user);
	
	
	
}
