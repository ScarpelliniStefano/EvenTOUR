package com.scarcolo.eventour.service.manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventManResponse;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.ManagerReportResponse;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.model.manager.ReportManResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.manager.RequestRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerService.
 */
@Service
public class RequestService {
	
	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;
	
	/** The request repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	/**
	 * Adds a request.
	 *
	 * @param request the request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public Request add(Request request) throws Exception{
		return requestRepository.save(request);
	}
	
	/**
	 * Update a request.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	public Request update(Request request) {
        Optional<Request> optionalRequest = requestRepository.findById(request.getId());
        if (optionalRequest.isEmpty()) {
            return null;
        }else {
        	Request r=optionalRequest.get();
        	if(r.getDateRenewal()!=request.getDateRenewal()) {
        		r.setDateRenewal(request.getDateRenewal());
        	}
        	if(r.isActive()!=request.isActive()) {
        		r.setActive(request.isActive());
        	}
        	requestRepository.save(r);
        	return r;
        }
    }
	
	/**
	 * Update a manager activation.
	 *
	 * @param id the id of manager
	 * @return the response entity
	 */
	public Request updateActivation(String id) {
        List<Request> optionalRequest = requestRepository.findByManagerId(id);
        if (optionalRequest.isEmpty()) {
            return null;
        }else {
        	Request r=optionalRequest.get(0);
        	r.setActive(!r.isActive());
        	requestRepository.save(r);
        	return r;
        }
    }
	
	/**
	 * Update a manager date.
	 *
	 * @param date the date of renewal
	 * @return the response entity
	 */
	public Request updateRenewal(String id) {
        List<Request> optionalRequest = requestRepository.findByManagerId(id);
        if (optionalRequest.isEmpty()) {
            return null;
        }else {
        	Request r=optionalRequest.get(0);
        	r.setDateRenewal(Functionalities.convertToDate(LocalDate.now()));
        	requestRepository.save(r);
        	return r;
        }
    }

   
	 /**
 	 * Gets the request by id.
 	 *
 	 * @param id the id manager
 	 * @return the request by id
 	 */
 	public Request getById(String id){
 			List<Request> requestData = requestRepository.findByManagerId(id);

	  	  if (!requestData.isEmpty()) {
	  	    return requestData.get(0);
	  	  } else {
	  	    return null;
	  	  }
	 }
  
    /**
     * Delete a manager.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        List<Request> optionalRequest = requestRepository.findByManagerId(id);
        if (optionalRequest.isEmpty()) {
            return false;
        }
        requestRepository.deleteById(optionalRequest.get(0).getId().toString());
        return true;
    }

	public List<ManagerPlusResponse> getAll(boolean active, boolean scaduto) {
		List<ManagerPlusResponse> man=null;
		List<Request> reqs=new ArrayList<>();
		if(active) {
			Date dt=Functionalities.convertToDate(LocalDate.now().minusYears(1));
			System.out.println(dt);
			reqs=requestRepository.findAllActive(Sort.by("dateRenewal").ascending());
			if(scaduto) {
				System.out.println("active scad");
				for(Request r : reqs) {
					if(r.getDateRenewal().after(dt)) 
						reqs.remove(r);
				}
			}else {
				System.out.println("active not scad");
				for(Request r : reqs) {
					if(r.getDateRenewal().before(dt)) 
						reqs.remove(r);
				}
			}
		}
		else
			reqs=requestRepository.findAllNotActive(Sort.by("dateRenewal").ascending());
		if(!reqs.isEmpty()) {
			man=new ArrayList<>();
			for(Request req:reqs) {
				man.add(new ManagerPlusResponse(managerRepository.findById(req.getManagerId().toString()).get(),req));
			}
		}
		return man;
	}

	

}