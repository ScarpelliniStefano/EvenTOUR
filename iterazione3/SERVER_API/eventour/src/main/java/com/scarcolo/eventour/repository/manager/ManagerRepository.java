package com.scarcolo.eventour.repository.manager;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.admin.ReportAdmResponse;
import com.scarcolo.eventour.model.manager.Manager;

// TODO: Auto-generated Javadoc
/**
 * The Interface ManagerRepository.
 */
public interface ManagerRepository extends MongoRepository<Manager, String>{
	
	/**
	 * Find all ticket inspectors of a manager.
	 *
	 * @param id the id manager
	 * @return the aggregation results
	 */
	 @Aggregation(pipeline = {" {\n"
			+ "        '$match': {\n"
			+ "            '_id': ObjectId('?0')\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': '_id', \n"
			+ "            'foreignField': 'managerId', \n"
			+ "            'as': 'events'\n"
			+ "        }\n"
			+ "    }",
			" {\n"
			+ "        '$project': {\n"
			+ "            '_id': 1, \n"
			+ "            'name': 1, \n"
			+ "            'surname': 1, \n"
			+ "            'mail': 1, \n"
			+ "            'events': {\n"
			+ "                '_id': 1, \n"
			+ "                'title': 1\n"
			+ "            }\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'ticketInspectors', \n"
			+ "            'localField': 'events._id', \n"
			+ "            'foreignField': 'eventId', \n"
			+ "            'as': 'events.ticketInsps'\n"
			+ "        }\n"
			+ "    }"})
	       AggregationResults<Object> findAllTicketInsps(ObjectId id);

	/**
	 * Find by mail.
	 *
	 * @param user the mail of manager
	 * @return the list
	 */
	List<Manager> findByMail(String user);

	
	/**
	 * Find reports.
	 *
	 * @return the aggregation results
	 */
	@Aggregation(pipeline = {" {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'events', \n"
			+ "            'localField': '_id', \n"
			+ "            'foreignField': 'managerId', \n"
			+ "            'as': 'events'\n"
			+ "        }\n"
			+ "    }"," {\n"
			+ "        '$lookup': {\n"
			+ "            'from': 'requests', \n"
			+ "            'localField': '_id', \n"
			+ "            'foreignField': 'managerId', \n"
			+ "            'as': 'request'\n"
			+ "        }\n"
			+ "    }","{\n"
			+ "        '$sort': {\n"
			+ "            'ragioneSociale': 1\n"
			+ "        }\n"
			+ "    }"})
	AggregationResults<ReportAdmResponse> findReports();

	
	
}