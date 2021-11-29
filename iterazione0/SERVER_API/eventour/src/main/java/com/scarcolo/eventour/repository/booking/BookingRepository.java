package com.scarcolo.eventour.repository.booking;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.EventBookedResponse;






public interface BookingRepository extends MongoRepository<Booking, String> {
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            'userId': ObjectId('?0')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': 'eventId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'event'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserId(ObjectId id);
	
	
	

	
	
	
}
