package com.scarcolo.eventour.repository.manager;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.manager.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String> {
	
}
