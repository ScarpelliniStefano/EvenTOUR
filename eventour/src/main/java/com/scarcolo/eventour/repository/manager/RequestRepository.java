package com.scarcolo.eventour.repository.manager;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.request.Request;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestRepository.
 */
public interface RequestRepository extends MongoRepository<Request, String>{

	
	List<Request> findByManagerId(String id);

	@Query("{active : true}")
	List<Request> findAllActive(Sort sorted);

	@Query("{active : false}")
	List<Request> findAllNotActive(Sort sorted);

	
	
}
