package com.scarcolo.eventour.service.manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerService.
 */
@Service
public class ManagerService {
	
	/**
	 * Adds a manager.
	 *
	 * @param request the request of new manager
	 * @return the response entity with new manager data
	 * @throws Exception exception of insert
	 */
	public ResponseEntity<ManagerResponse> add(AddManagerRequest request) throws Exception{
		Manager manager;
		try {
			manager = new Manager(request);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(new ManagerResponse(manager), HttpStatus.OK);
	}
	
	


}
