package com.scarcolo.eventour.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.admin.AdminReportResponse;
import com.scarcolo.eventour.service.admin.AdminService;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AdminController {
	
	/** The admin service. */
	@Autowired
	private AdminService adminService;
	 
	   
	    /**
    	 * Gets the admin by id.
    	 *
    	 * @param id the id
    	 * @return the manager by id
    	 */
    	@GetMapping("/admins/id={id}")
	    public ResponseEntity<AdminResponse> getAdminById(@PathVariable("id") String id){
	        return adminService.getById(id);
	    }
	    

	    /**
    	 * Get all admins.
    	 *
    	 * @return the all managers
    	 */
    	@GetMapping("/admins")
	    public ResponseEntity<List<AdminResponse>> getAllAdmins(){
	        return adminService.getAll();
	    }
	    
	    /**
    	 * Gets the manager from a id event.
    	 *
    	 * @param id the id of event
    	 * @return the manager from id event
    	 */
    	@GetMapping("/admins/{mail}")
	    public ResponseEntity<AdminResponse> getAdminFromMail(@PathVariable("mail") String mail){
	        return adminService.getAdminFromMail(mail);
	    }
    	
    	/**
    	 * Gets the report of admin.
    	 *
    	 * @return the reports
    	 */
    	@GetMapping("/admins/reports")
	    public ResponseEntity<List<AdminReportResponse>> getAdminReport(){
	        return adminService.getAdminReport();
	    }
    	
    	/**
    	 * Get requests.
    	 *
    	 * @return the all managers
    	 */
    	@GetMapping("/admins/requests")
	    public ResponseEntity<List<ManagerPlusResponse>> getRequests(@RequestParam(defaultValue = "1") int active, @RequestParam(defaultValue = "0") int scadute){
    		return adminService.getRequest(active>0?true:false,scadute>0?true : false);
	    }

	   
	    /**
    	 * Delete by id.
    	 *
    	 * @param id the id
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/admins/{id}")
	    public boolean deleteById(@RequestParam String id){
	        return adminService.delete(id);
	    }

}