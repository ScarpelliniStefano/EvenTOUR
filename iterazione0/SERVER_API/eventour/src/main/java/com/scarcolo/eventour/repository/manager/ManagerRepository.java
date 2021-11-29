package com.scarcolo.eventour.repository.manager;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.scarcolo.eventour.model.manager.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String>{


	List<Manager> findByMail(String user);
	
}
