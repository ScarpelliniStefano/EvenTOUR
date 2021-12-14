package com.scarcolo.eventour.repository.admin;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.admin.Admin;
import com.scarcolo.eventour.model.manager.Manager;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerRepository.
 */
public interface AdminRepository extends MongoRepository<Admin, String>{
	
	

	/**
	 * Find by mail.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Admin> findByMail(String user);

	
	
}
