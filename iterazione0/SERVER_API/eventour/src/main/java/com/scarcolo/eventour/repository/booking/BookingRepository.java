package com.scarcolo.eventour.repository.booking;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.EventBookedResponse;


// TODO: Auto-generated Javadoc
/**
 * The Interface BookingRepository.
 */
public interface BookingRepository extends MongoRepository<Booking, String> {
	
	/**
	 * Find by user id.
	 *
	 * @param id the id
	 * @return the aggregation results
	 */
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

	/**
	 * Find by user id and event id.
	 *
	 * @param id the id
	 * @param idE the id event
	 * @return the aggregation results
	 */
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            'userId': ObjectId('?0')\n"
			+ "            'eventId': ObjectId('?1')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': 'eventId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'event'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserIdAndEventId(String id, String idE);
	
	
	

	
	
	
}
