package com.scarcolo.eventour.repository.ticketisp;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.ticketinsp.TicketInsp;






public interface TicketInspRepository extends MongoRepository<TicketInsp, String> {
	@Query("{eventId:new ObjectId(?0)}")
	List<TicketInsp> findByEventId(ObjectId objectId);
	
	/*@Query("evenTour.events.aggregate([{$lookup:{from: 'ticketInsps',"+
	        "localField: 'eventId',foreignField: '_id', as: 'ticketInsps_event'}},"+
			"$match: {'events.managerId': ?0 }"+
	        "{$project:{item_id: ?0, fullName: '$ticketInsps_event.fullName',"+
			"code: '$ticketInsps_event.code'} 	}])")
	List<Object> findByManagerId(String managerId);*/
}
