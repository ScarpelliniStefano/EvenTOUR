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

import com.scarcolo.eventour.model.booking.UserEventBookedResponse;
import com.scarcolo.eventour.model.event.EventManResponse;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	public ResponseEntity<ManagerResponse> add(AddManagerRequest request) throws Exception{
		Manager Manager = managerRepository.save(new Manager(request));
		return new ResponseEntity<>(new ManagerResponse(Manager), HttpStatus.OK);
	}
	
	public ResponseEntity<ManagerResponse> update(EditManagerRequest request) {
        Optional<Manager> optionalManager = managerRepository.findById(request.id);
        if (optionalManager.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new ManagerResponse(optionalManager.get()), HttpStatus.OK);
    }

   
	 public ResponseEntity<ManagerResponse> getById(String id){
	    	Optional<Manager> managerData = managerRepository.findById(id);

	  	  if (managerData.isPresent()) {
	  	    return new ResponseEntity<>(new ManagerResponse(managerData.get()), HttpStatus.OK);
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
	 }
  
    public boolean delete(String id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isEmpty()) {
            return false;
        }
        managerRepository.deleteById(optionalManager.get().getId().toString());
        return true;
    }

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

	@Autowired
	private EventRepository eventRepository;
	
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


}
