package com.scarcolo.eventour.repository.ticketisp;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.ticketinsp.TicketInsp;






public interface TicketInspRepository extends MongoRepository<TicketInsp, String> {
	@Query("{eventId:new ObjectId(?0)}")
	List<TicketInsp> findByEventId(ObjectId objectId);

	List<TicketInsp> findByCode(String code);
	
}
