package com.scarcolo.eventour.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.repository.manager.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	public ResponseEntity<Manager> add(AddManagerRequest request) throws Exception{
		Manager Manager = managerRepository.save(new Manager(request));
		return new ResponseEntity<>(Manager, HttpStatus.OK);
	}
	
	public ResponseEntity<Manager> update(EditManagerRequest request) {
        Optional<Manager> optionalManager = managerRepository.findById(request.id);
        if (optionalManager.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalManager.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<Manager> getById(String id) {
    	Optional<Manager> ManagerData = managerRepository.findById(id);

  	  if (ManagerData.isPresent()) {
  	    return new ResponseEntity<>(ManagerData.get(), HttpStatus.OK);
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

	public ResponseEntity<List<Manager>> getAll() {
		try {
			List<Manager> Managers = new ArrayList<>();
			managerRepository.findAll().forEach(Managers::add);
			if(Managers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Managers, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
