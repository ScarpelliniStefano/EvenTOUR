package com.scarcolo.eventour.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.EventReportResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.manager.ReportManResponse;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerService.
 */
@Service
public class ManagerService {
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	/** The event repository. */
	@Autowired
	private EventRepository eventRepository;
	
	/**
	 * Adds a manager.
	 *
	 * @param request the request of new manager
	 * @return the response entity with new manager data
	 * @throws Exception the exception if no manager is added
	 */
	public ResponseEntity<ManagerResponse> add(AddManagerRequest request) throws Exception{
		Manager Manager = managerRepository.save(new Manager(request));
		return new ResponseEntity<>(new ManagerResponse(Manager), HttpStatus.OK);
	}
	
	/**
	 * Update a manager.
	 *
	 * @param request the request with data to change
	 * @return the response entity with modified data
	 */
	public ResponseEntity<ManagerResponse> update(EditManagerRequest request) {
        Optional<Manager> optionalManager = managerRepository.findById(request.id);
        if (optionalManager.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new ManagerResponse(optionalManager.get()), HttpStatus.OK);
    }

   
	 /**
 	 * Gets the manager by id.
 	 *
 	 * @param id the id of manager
 	 * @return the manager by id
 	 */
 	public ResponseEntity<ManagerResponse> getById(String id){
	    	Optional<Manager> managerData = managerRepository.findById(id);

	  	  if (managerData.isPresent()) {
	  	    return new ResponseEntity<>(new ManagerResponse(managerData.get()), HttpStatus.OK);
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
	 }
  
    /**
     * Delete a manager.
     *
     * @param id the id of manager
     * @return true, if successful
     */
     public boolean delete(String id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isEmpty()) {
            return false;
        }
        managerRepository.deleteById(optionalManager.get().getId().toString());
        return true;
    }

	/**
	 * Gets all managers.
	 *
	 * @return all managers
	 */
	public ResponseEntity<List<ManagerResponse>> getAll() {
		try {
			List<Manager> managers = new ArrayList<>();
			managerRepository.findAll().forEach(managers::add);
			if(managers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<ManagerResponse> managerR= new ArrayList<>();
			for(Manager manager: managers) managerR.add(new ManagerResponse(manager));
			return new ResponseEntity<>(managerR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	


	/**
	 * Gets the manager report.
	 *
	 * @param id the id of manager
	 * @return the manager report
	 */
	public ResponseEntity<List<EventReportResponse>> getManagerReport(String id) {
		try {
			AggregationResults<ReportManResponse> reportA=eventRepository.findReports(new ObjectId(id));
			List<ReportManResponse> reportMongo=reportA.getMappedResults();
			List<EventReportResponse> reportR=new ArrayList<>();
			Integer occuped=0;
			Integer comedPeople=0;
			Double saldo=0d;
			Double perdita=0d;
			Event eventDetails=null;
			for(ReportManResponse resp : reportMongo) {
				
				occuped=resp.getTotSeat()-resp.getFreeSeat();
				comedPeople=0;
				for(Booking book: resp.getBooking()) {
					comedPeople+=(book.getCome()==true ? book.getPrenotedSeat() : 0);
				}
				saldo=comedPeople*resp.getPrice();
				perdita=(occuped-comedPeople)*resp.getPrice();
				eventDetails=new Event(resp.getId(), resp.getTitle(), resp.getDescription(), resp.getLocation(), resp.getTypes(),
						resp.getDataOra(), resp.getManagerId(),resp.getUrlImage(), resp.getTotSeat(), resp.getFreeSeat(), resp.getPrice());
				reportR.add(new EventReportResponse(resp.getId(), resp.getTitle(), new EventResponse(eventDetails), occuped, comedPeople, saldo, perdita));
			}
			
			return new ResponseEntity<>(reportR, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}