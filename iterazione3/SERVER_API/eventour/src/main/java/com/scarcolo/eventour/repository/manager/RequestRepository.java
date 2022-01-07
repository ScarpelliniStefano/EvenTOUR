package com.scarcolo.eventour.repository.manager;


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

	/**
	 * Find by manager id.
	 *
	 * @param id the id of manager
	 * @return the list
	 */
	@Query("{managerId : new ObjectId('?0')}")
	List<Request> findByManagerId(String id);

	/**
	 * Find all active.
	 *
	 * @param sorted the sorted
	 * @return the list
	 */
	@Query("{active : true}")
	List<Request> findAllActive(Sort sorted);

	/**
	 * Find all not active.
	 *
	 * @param sorted the sorted
	 * @return the list
	 */
	@Query("{active : false}")
	List<Request> findAllNotActive(Sort sorted);

	
	
}
