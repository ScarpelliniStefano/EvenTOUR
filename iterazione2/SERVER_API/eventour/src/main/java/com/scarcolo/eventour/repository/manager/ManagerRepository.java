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
	 * @param user the manager id
	 * @return the manager with this mail
	 */
	List<Manager> findByMail(String user);

	
	
}
