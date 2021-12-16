package com.scarcolo.eventour.service.manager;

import java.time.LocalDateTime;
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

import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventManResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.ManagerReportResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.manager.ReportManResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.manager.RequestRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerService.
 */
@Service
public class ManagerService {
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	/** The manager repository. */
	@Autowired
	private RequestRepository requestRepository;	
	
	/** The event repository. */
	@Autowired
	private EventRepository eventRepository;
	
	/**
	 * Adds a manager.
	 *
	 * @param request the request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<ManagerResponse> add(AddManagerRequest request) throws Exception{
		Manager Manager = managerRepository.save(new Manager(request));
		requestRepository.save(new Request(Manager.getId()));
		return new ResponseEntity<>(new ManagerResponse(Manager), HttpStatus.OK);
	}
	
	/**
	 * Update a manager.
	 *
	 * @param request the request
	 * @return the response entity
	 * @throws AddressException 
	 */
	public ResponseEntity<ManagerResponse> update(EditManagerRequest request) throws AddressException {
        Optional<Manager> optionalManager = managerRepository.findById(request.id);
        if (optionalManager.isEmpty()) {
            return null;
        }
        Manager m=optionalManager.get();
        if(request.residence!=null) {
        	m.setResidence(request.residence);
        }
        if(request.mail!=null) {
        	m.setMail(request.mail);
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
 	 * @param id the id
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
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isEmpty()) {
            return false;
        }
        managerRepository.deleteById(optionalManager.get().getId().toString());
        Request r=requestRepository.findByManagerId(optionalManager.get().getId().toString()).get(0);
        requestRepository.deleteById(r.getId());
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
	 * Gets a manager from event id.
	 *
	 * @param id the event id
	 * @return the manager from event
	 */
	public ResponseEntity<EventManResponse> getManagerFromEvent(String id) {
		try {
			AggregationResults<EventManResponse> userEventA=eventRepository.findManagerById(new ObjectId(id));
			List<EventManResponse> eventR=userEventA.getMappedResults();
			return new ResponseEntity<>(eventR.get(0), HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<ManagerReportResponse>> getManagerReport(String id) {
		try {
			AggregationResults<ReportManResponse> reportA=eventRepository.findReports(new ObjectId(id));
			List<ReportManResponse> reportMongo=reportA.getMappedResults();
			List<ManagerReportResponse> reportR=new ArrayList<>();
			Integer occuped=0;
			Integer comedPeople=0;
			Double saldo=0d;
			Double perdita=0d;
			Event eventDetails=null;
			Double review=0d;
			for(ReportManResponse resp : reportMongo) {
				if(resp.getDataOra().isBefore(LocalDateTime.now())) {
					occuped=resp.getTotSeat()-resp.getFreeSeat();
					comedPeople=0;
					for(Booking book: resp.getBooking()) {
						comedPeople+=(book.getCome()==true ? book.getPrenotedSeat() : 0);
						review+=book.getReview()>0 ? book.getReview() : 0;
					}
					review=review/(resp.getBooking().length);
					saldo=comedPeople*resp.getPrice();
					perdita=resp.getFreeSeat()*resp.getPrice();
					eventDetails=new Event(resp.getId(), resp.getTitle(), resp.getDescription(), resp.getLocation(), resp.getTypes(),
							resp.getDataOra(), resp.getManagerId(),resp.getUrlImage(), resp.getTotSeat(), resp.getFreeSeat(), resp.getPrice());
					reportR.add(new ManagerReportResponse(resp.getId(), resp.getTitle(), new EventResponse(eventDetails), occuped, comedPeople, saldo, perdita, review));
			
				}
			}
			
			return new ResponseEntity<>(reportR, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
