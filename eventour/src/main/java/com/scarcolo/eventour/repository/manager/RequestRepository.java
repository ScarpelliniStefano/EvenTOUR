package com.scarcolo.eventour.repository.manager;


import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ReportManResponse;
import com.scarcolo.eventour.model.request.Request;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerRepository.
 */
public interface RequestRepository extends MongoRepository<Request, String>{

	
	List<Request> findByManagerId(String id);

	
	
}
