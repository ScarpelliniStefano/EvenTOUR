package com.scarcolo.eventour.repository.ticketisp;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.ticketinsp.TicketInsp;






// TODO: Auto-generated Javadoc
/**
 * The Interface TicketInspRepository.
 */
public interface TicketInspRepository extends MongoRepository<TicketInsp, String> {
	
	/**
	 * Find by event id.
	 *
	 * @param objectId the object id
	 * @return the list
	 */
	@Query("{eventId:new ObjectId(?0)}")
	List<TicketInsp> findByEventId(ObjectId objectId);

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the list
	 */
	List<TicketInsp> findByCode(String code);
	
}
