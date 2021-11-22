package com.scarcolo.eventour.repository.manager;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scarcolo.eventour.model.manager.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String>{
	@Query("{eventId:new ObjectId(?0)}")
	/*@Aggregation(pipeline = {"{$project: {\n" +
	        "        month: {$month: $poDate},\n" +
	        "        year: {$year: $poDate},\n" +
	        "        amount: 1,\n" +
	        "        poDate: 1\n" +
	        "     }}"
	        ,"{$match: {$and : [{year:?0} , {month:?1}] \n" +
	        "     }}"
	        ,"{$group: { \n" +
	        "          '_id': {\n" +
	        "            month: {$month: $poDate},\n" +
	        "            year: {$year: $poDate} \n" +
	        "          },\n" +
	        "          totalPrice: {$sum: {$toDecimal:$amount}},\n" +
	        "          }\n" +
	        "      }"
	    ,"{$project: {\n" +
	        "        _id: 0,\n" +
	        "        totalPrice: {$toString: $totalPrice}\n" +
	        "     }}"})
	    AggregationResults<SumPrice> sumPriceThisYearMonth(Integer year, Integer month);*/
	List<Object> findAllTicketInsps(ObjectId objectId);
	
}
