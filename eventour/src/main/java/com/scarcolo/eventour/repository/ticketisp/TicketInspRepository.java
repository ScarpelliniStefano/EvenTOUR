package com.scarcolo.eventour.repository.ticketisp;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.ticketinsp.TicketInsp;






public interface TicketInspRepository extends MongoRepository<TicketInsp, String> {
	
	
	
}
