package com.scarcolo.eventour.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.manager.AddManagerRequest;
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
	 	 * @throws Exception the exception if add manager is not possible
	 	 */
	 	@PostMapping("/managers")
	    public ResponseEntity<ManagerResponse> addManager(@RequestBody AddManagerRequest request) throws Exception{
	      return managerService.add(request);
	    }

}
