package com.scarcolo.eventour.repository.admin;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.admin.Admin;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerRepository.
 */
public interface AdminRepository extends MongoRepository<Admin, String>{
	
	

	/**
	 * Find by mail.
	 *
	 * @param user the username
	 * @return the list of admin
	 */
	List<Admin> findByMail(String user);

	
	
}
