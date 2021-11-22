package com.scarcolo.eventour.repository.event;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.event.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	@Query("{dataOra: {$gt: new Date()} }")
	Page<Event> findAllFuture(Pageable paging);
	
	@Query("{ dataOra : { $gte: ?0, $lte: ?1, $gt : new Date() }}")
	Page<Event> findByDataOraBetweenOrderByDataOraAsc(Date dataOraGT, Date dataOraLT, Pageable paging);
	
	@Query("{types : {$in : ?0}, dataOra: {$gt: new Date()} }")
	Page<Event> findByTypes(String[] type, Pageable paging);
	
	@Query("{freeSeat: {$gt: 0} , dataOra: {$gt: new Date()} }")
	Page<Event> findByfreeSeatGreaterThanZero(Pageable paging);
	
	@Query("{dataOra: {$gt: new Date()} }")
	Page<Event> findByManagerId(ObjectId managerId,Pageable paging);
	
	@Query("{ 'location.regione' : { '$regex' : ?0 , $options: 'i'}, dataOra: {$gt: new Date()} }")
	Page<Event> findByLocation_RegioneLike(String regione, Pageable paging);
	
	@Query("{ 'location.provincia' : { '$regex' : ?0 , $options: 'i'}, dataOra: {$gt: new Date()} }")
	Page<Event> findByLocation_ProvinciaLike(String provincia, Pageable paging);
	
	@Query("{ 'location.city' : { '$regex' : ?0 , $options: 'i'}, dataOra: {$gt: new Date()} }")
	Page<Event> findByLocation_CityLike(String city, Pageable paging);
	//List<Event> findByPreferences(String preferences);
	
	List<Event> findByDataOraBetweenOrderByDataOraAsc(Date dataOraGT, Date dataOraLT);
	
	@Query("{types : {$in : ?0}}")
	List<Event> findByTypes(String[] type);
	
	@Query("{freeSeat: {$gt: 0} }")
	List<Event> findByfreeSeatGreaterThanZero(Sort sort);
	
	List<Event> findByManagerId(String managerId);
	
	@Query("{ 'location.regione' : { '$regex' : ?0 , $options: 'i'}}")
	List<Event> findByLocation_RegioneLike(String regione);
	
	@Query("{ 'location.provincia' : { '$regex' : ?0 , $options: 'i'}}")
	List<Event> findByLocation_ProvinciaLike(String provincia);
	
	@Query("{ 'location.city' : { '$regex' : ?0 , $options: 'i'}}")
	List<Event> findByLocation_CityLike(String city);


	

}
