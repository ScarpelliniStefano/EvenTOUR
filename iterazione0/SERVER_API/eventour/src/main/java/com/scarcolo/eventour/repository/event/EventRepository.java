package com.scarcolo.eventour.repository.event;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.event.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	@Query("{dataOra: {$gt: new Date()} }")
	Page<Event> findAllFuture(Pageable paging);

	@Query("{managerId:new ObjectId(?0), dataOra: {$gt: new Date()} }")
	Page<Event> findByManagerId(ObjectId objectId,Pageable paging);
	
	



	

}
