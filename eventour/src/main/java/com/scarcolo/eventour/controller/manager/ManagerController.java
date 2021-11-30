package com.scarcolo.eventour.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.event.EventManResponse;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;
import com.scarcolo.eventour.service.manager.ManagerService;


// TODO: Auto-generated Javadoc
/**
 * The Class ManagerController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ManagerController {
	
	/** The manager service. */
	@Autowired
	private ManagerService managerService;
	 
 	/**
 	 * Adds the manager.
 	 *
 	 * @param request the request with data of new manager
 	 * @return the response entity
 	 * @throws Exception the exception
 	 */
 	@PostMapping("/managers")
	    public ResponseEntity<ManagerResponse> addManager(@RequestBody AddManagerRequest request) throws Exception{
	      return managerService.add(request);
	    }

	   
	    /**
    	 * Update a manager.
    	 *
    	 * @param request the request
    	 * @return the response entity
    	 */
    	@PutMapping("/managers")
	    public ResponseEntity<ManagerResponse> updateManager(@RequestBody EditManagerRequest request){
	        return managerService.update(request);
	    }


	   
	    /**
    	 * Gets the manager by id.
    	 *
    	 * @param id the id
    	 * @return the manager by id
    	 */
    	@GetMapping("/managers/{id}")
	    public ResponseEntity<ManagerResponse> getManagerById(@PathVariable("id") String id){
	        return managerService.getById(id);
	    }
	    

	    /**
    	 * Get all managers.
    	 *
    	 * @return the all managers
    	 */
    	@GetMapping("/managers")
	    public ResponseEntity<List<ManagerResponse>> getAllManagers(){
	        return managerService.getAll();
	    }
	    
	    /**
    	 * Gets the manager from a id event.
    	 *
    	 * @param id the id of event
    	 * @return the manager from id event
    	 */
    	@GetMapping("/managers/event/{id}")
	    public ResponseEntity<EventManResponse> getManagerFromIdEvent(@PathVariable("id") String id){
	        return managerService.getManagerFromEvent(id);
	    }

	   
	    /**
    	 * Delete by id.
    	 *
    	 * @param id the id
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/managers/{id}")
	    public boolean deleteById(@RequestParam String id){
	        return managerService.delete(id);
	    }

}
