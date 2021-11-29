package com.scarcolo.eventour.repository.booking;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.user.UserBookedResponse;






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
	
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            'eventId': ObjectId('?0')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'users', \n"
			+ "            'localField': 'userId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'user'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<UserBookedResponse> findByEventId(ObjectId objectId);

	
	@Aggregation(pipeline = {" {\n"
			+ "        '$match': {\n"
			+ "            '_id': ObjectId('?0')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'users', \n"
			+ "            'localField': 'userId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'user'\n"
			+ "            	'userId': ObjectId('?0')\n"
			+ "				'eventId': ObjectId('?1')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': 'eventId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'event'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<UserEventBookedResponse> findByIdDetails(ObjectId objectId);
	
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            	'userId': ObjectId('?0')\n"
			+ "				'eventId': ObjectId('?1')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': 'eventId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'event'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserAndEvent(ObjectId id, ObjectId idEv);

	

	
	
	
}
