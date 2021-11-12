package com.scarcolo.eventour.repository.booking;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.booking.Booking;






public interface BookingRepository extends MongoRepository<Booking, String> {
	
	
	
}
