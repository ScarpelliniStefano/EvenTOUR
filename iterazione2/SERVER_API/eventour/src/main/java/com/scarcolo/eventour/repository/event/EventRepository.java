package com.scarcolo.eventour.repository.event;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventManResponse;
import com.scarcolo.eventour.model.manager.ReportManResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventRepository.
 */
public interface EventRepository extends MongoRepository<Event, String> {
	
	/**
	 * Find all future.
	 *
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{dataOra: {$gt: new Date()} }")
	Page<Event> findAllFuture(Pageable paging);
	
	/**
	 * Find by data ora between two dates, order by data ora asc.
	 *
	 * @param dataOraGT the data ora greater than (lower limit)
	 * @param dataOraLT the data ora less than (greater limit)
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{ dataOra : { $gte: ?0, $lt: ?1, $gt : new Date() }}")
	Page<Event> findByDataOraBetweenOrderByDataOraAsc(Date dataOraGT, Date dataOraLT, Pageable paging);
	
	/**
	 * Find by types.
	 *
	 * @param type the types array
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{types : {$in : ?0}, dataOra: {$gt: new Date()} }")
	Page<Event> findByTypes(String[] type, Pageable paging);
	
	/**
	 * Find by types and location regione like.
	 *
	 * @param type the type
	 * @param regione the regione
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{types : {$in : ?0}, 'location.regione' : { '$regex' : ?1 , $options: 'i'}, dataOra: {$gt: new Date()}}")
	Page<Event> findByTypesAndLocationRegioneLike(String[] type,String regione, Pageable paging);
	/**
	 * Find by free seat greater than zero.
	 *
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{freeSeat: {$gt: 0} , dataOra: {$gt: new Date()} }")
	Page<Event> findByFreeSeatGreaterThanZero(Pageable paging);
	
	/**
	 * Find by manager id.
	 *
	 * @param objectId the object id
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{managerId:new ObjectId(?0), dataOra: {$gt: new Date()} }")
	Page<Event> findByManagerId(ObjectId objectId,Pageable paging);
	
	/**
	 * Find by location like.
	 *
	 * @param type the name of location
	 * @param loc the loc
	 * @param paging the paging
	 * @return the page
	 */
	@Query("{ '?0' : { '$regex' : ?1 , $options: 'i'}, dataOra: {$gt: new Date()} }")
	Page<Event> findByLocationLike(String type,String loc, Pageable paging);
	
	
	//List<Event> findByPreferences(String preferences);
	
	/**
	 * Find by data ora between two dates, order by data ora asc.
	 *
	 * @param dataOraGT the data ora greater than (lower limit)
	 * @param dataOraLT the data ora less than (greater limit)
	 * @return the list
	 */
	List<Event> findByDataOraBetweenOrderByDataOraAsc(Date dataOraGT, Date dataOraLT);
	
	/**
	 * Find by types.
	 *
	 * @param type the type
	 * @return the list
	 */
	@Query("{types : {$in : ?0}}")
	List<Event> findByTypes(String[] type);
	
	
	
	/**
	 * Find by free seat greater than zero.
	 *
	 * @param sort the sort
	 * @return the list
	 */
	@Query("{freeSeat: {$gt: 0} }")
	List<Event> findByfreeSeatGreaterThanZero(Sort sort);
	
	/**
	 * Find by manager id.
	 *
	 * @param managerId the manager id
	 * @return the list
	 */
	List<Event> findByManagerId(String managerId);
	
	/**
	 * Find by location like.
	 *
	 * @param type the type of event
	 * @param loc the location (region) to filter
	 * @return the list
	 */
	@Query("{ '?0' : { '$regex' : ?1 , $options: 'i'}}")
	List<Event> findByLocationLike(String type,String loc);
	

	/**
	 * Find by location regione like and free seat greater than zero.
	 *
	 * @param regione the regione to filter
	 * @param sorted the sorted settings
	 * @return the list
	 */
	@Query("{ 'location.regione' : { '$regex' : ?0 , $options: 'i'}, 'freeSeat': {$gt: 0},  'dataOra': {$gt: new Date()} }")
	List<Event> findByLocationRegioneLikeAndFreeSeatGreaterThanZero(String regione,Sort sorted);

	/**
	 * Find manager of a event.
	 *
	 * @param id the manager id
	 * @return the aggregation results
	 */
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            	'_id': ObjectId('?0')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'managers', \n"
			+ "            'localField': 'managerId', \n"
			+ "            'foreignField': '_id', \n"
			+ "            'as': 'manager'\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<EventManResponse> findManagerById(String id);

	
	/**
	 * Find reports.
	 *
	 * @param id the id
	 * @return the aggregation results
	 */
	@Aggregation(pipeline = {"{\n"
			+ "        '$match': {\n"
			+ "            'managerId': ObjectId('?0'),\n"
			+ "            'dataOra': {$lt: new Date()},\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'bookings', \n"
			+ "            'localField': '_id', \n"
			+ "            'foreignField': 'eventId', \n"
			+ "            'as': 'booking'\n"
			+ "        }\n"
			+ "    }","{\n"
			+ "        '$sort': {\n"
			+ "            'dataOra': 1\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<ReportManResponse> findReports(String id);

	
	/**
	 * Find by free seat greater than numPersone.
	 *
	 * @param numPers the num pers
	 * @param ascending the ascending
	 * @return the list
	 */
	@Query("{freeSeat: {$gte: ?0}, dataOra:{$gt: new Date()} }")
	List<Event> findByfreeSeatGreaterThanNumPersone(Integer numPers, Sort ascending);

}
