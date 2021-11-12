package com.scarcolo.eventour.repository.ticketisp;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.ticketisp.TicketIsp;

public interface TicketIspRepository extends MongoRepository<TicketIsp, String> {

}
