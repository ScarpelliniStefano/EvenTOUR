package com.scarcolo.eventour.repository.event;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.event.Event;





public interface EventRepository extends MongoRepository<Event, String> {
	
	
	
}
