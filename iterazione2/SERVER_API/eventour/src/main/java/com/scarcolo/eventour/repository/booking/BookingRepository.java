package com.scarcolo.eventour.repository.booking;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.EventBookedResponse;
import com.scarcolo.eventour.model.user.UserBookedResponse;


// TODO: Auto-generated Javadoc
/**
 * The Interface BookingRepository.
 */
public interface BookingRepository extends MongoRepository<Booking, String> {
	
	/**
	 * Find by user id.
	 *
	 * @param userId the id
	 * @param page_size the page size
	 * @param size the size
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
			+ "    }","{\n"
			+ "        '$skip': ?1\n"
			+ "    }"," {\n"
			+ "        '$limit': ?2\n"
			+ "    }"," {\n"
			+ "        '$sort': {\n"
			+ "            'event.dataOra': 1\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserId(ObjectId userId,int page_size, int size);
	
	/**
	 * Find by user id future.
	 *
	 * @param userId the id
	 * @param page_size the page size
	 * @param size the size
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
			+ "    }","{\n"
			+ "        '$match': {\n"
			+ "            'event.dataOra': {$gt: new Date() }\n"
			+ "        }\n"
			+ "    }"+"{\n"
			+ "        '$skip': ?1\n"
			+ "    }"," {\n"
			+ "        '$limit': ?2\n"
			+ "    }"," {\n"
			+ "        '$sort': {\n"
			+ "            'event.dataOra': 1\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserIdFuture(ObjectId userId,int page_size, int size);
	
	/**
	 * Find by user id past
	 *
	 * @param userId the id
	 * @param page_size the page size
	 * @param size the size
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
			+ "    }","{\n"
			+ "        '$match': {\n"
			+ "            'event.dataOra': {$lt: new Date() }\n"
			+ "        }\n"
			+ "    }"+"{\n"
			+ "        '$skip': ?1\n"
			+ "    }"," {\n"
			+ "        '$limit': ?2\n"
			+ "    }"," {\n"
			+ "        '$sort': {\n"
			+ "            'event.dataOra': 1\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventBookedResponse> findByUserIdPast(ObjectId userId,int page_size, int size);
	
	/**
	 * Find by user id.
	 *
	 * @param userId the id of user
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
			+ "    }"," {\n"
			+ "        '$sort': {\n"
			+ "            'event.dataOra': 1\n"
			+ "        }\n"
			+ "    }"})
	List<EventBookedResponse> findByUserId(ObjectId userId);
	
	/**
	 * Find by event id.
	 *
	 * @param eventId the eventId
	 * @return the aggregation results
	 */
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
	AggregationResults<UserBookedResponse> findByEventId(ObjectId eventId);

	
	/**
	 * Find by id, with details.
	 *
	 * @param id the id of booking
	 * @return the aggregation results
	 */
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
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': 'eventId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'event'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<UserEventBookedResponse> findByIdDetails(ObjectId id);
	
	/**
	 * Find by user and event.
	 *
	 * @param idUser the id of user
	 * @param idEvent the id of event
	 * @return the aggregation results
	 */
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
	AggregationResults<EventBookedResponse> findByUserAndEvent(ObjectId idUser, ObjectId idEvent);

	

	
	
	
}
