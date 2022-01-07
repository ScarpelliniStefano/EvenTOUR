package com.scarcolo.eventour.repository.manager;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.scarcolo.eventour.model.manager.Manager;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerRepository.
 */
public interface ManagerRepository extends MongoRepository<Manager, String>{


	/**
	 * Find by mail.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Manager> findByMail(String user);
	
}
