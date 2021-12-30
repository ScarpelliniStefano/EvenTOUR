package com.scarcolo.eventour.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;

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
import com.scarcolo.eventour.model.user.User;
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
	 */
	public ResponseEntity<ManagerResponse> add(AddManagerRequest request){
		Manager manager;
		try {
			manager = managerRepository.save(new Manager(request));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(new ManagerResponse(manager), HttpStatus.OK);
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
        Manager m=optionalManager.get();
        if(request.residence!=null) {
        	m.setResidence(request.residence);
        }
        if(request.mail!=null) {
        	try {
				m.setMail(request.mail);
			} catch (AddressException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
        }
        if(request.password!=null) {
        	m.setPassword(request.password);
        }
        Manager man=managerRepository.save(m);
        return new ResponseEntity<>(new ManagerResponse(man), HttpStatus.OK);
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
     public ResponseEntity<Boolean> delete(String id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        managerRepository.deleteById(optionalManager.get().getId());
        return new ResponseEntity<>(true,HttpStatus.OK);
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
			Optional<Manager> manOpt=managerRepository.findById(id);
			if(manOpt.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			AggregationResults<ReportManResponse> reportA=eventRepository.findReports(id);
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
			//System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
