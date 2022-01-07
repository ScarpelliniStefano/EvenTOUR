package com.scarcolo.eventour.service.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.repository.manager.RequestRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestService.
 */
@Service
public class RequestService {
	
	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
	
	/**
	 * Adds a request.
	 *
	 * @param request the request to insert
	 * @return the request inserted
	 */
	public Request add(Request request){
		return requestRepository.save(request);
	}
	
	/**
	 * Update a request.
	 *
	 * @param request the request to be updated
	 * @return the rupdated request
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
	 * @return the request updated
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
	 * Update a manager date of renewal.
	 *
	 * @param id to witch update renewal
	 * @return the request updated
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
 	 * Gets the request by id manager.
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
     * @param id the id manager
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

	/**
	 * Gets all managers with request associated, based on parames.
	 *
	 * @param active true if you want manager active
	 * @param scaduto true if you want manager with no renewal done until now (only if active is true)
	 * @return all managers finded
	 */
	public List<ManagerPlusResponse> getAll(boolean active, boolean scaduto) {
		List<ManagerPlusResponse> man=null;
		List<Request> reqs=new ArrayList<>();
		if(active) {
			Date dt=Functionalities.convertToDate(LocalDate.now().minusYears(1));
			//System.out.println(dt);
			reqs=requestRepository.findAllActive(Sort.by("dateRenewal").ascending());
			if(scaduto) {
				//System.out.println("active scad");
				reqs.removeIf(e -> e.getDateRenewal().after(dt));
			}else {
				//System.out.println("active not scad");
				reqs.removeIf(e -> e.getDateRenewal().before(dt));
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
