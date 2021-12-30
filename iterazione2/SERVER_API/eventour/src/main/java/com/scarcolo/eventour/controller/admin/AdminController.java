package com.scarcolo.eventour.controller.admin;

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

import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.admin.EditAdminRequest;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.admin.AddAdminRequest;
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
		 * Add a admin.
		 *
		 * @param request the request
		 * @return the response entity
		 */
		@PostMapping("/admins")
		public ResponseEntity<AdminResponse> addBooking(@RequestBody AddAdminRequest request){
		      return adminService.add(request);
		}

		   
		    /**
	    	 * Update admin.
	    	 *
	    	 * @param request the request
	    	 * @return the response entity
	    	 */
	    	@PutMapping("/admins")
		    public ResponseEntity<AdminResponse> updateBooking(@RequestBody EditAdminRequest request){
		        return adminService.update(request);
		    }
	   
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
    	 * @return all managers
    	 */
    	@GetMapping("/admins")
	    public ResponseEntity<List<AdminResponse>> getAllAdmins(){
	        return adminService.getAll();
	    }
	    
	    /**
    	 * Gets the admin from mail.
    	 *
    	 * @param mail the mail
    	 * @return the admin with specified mail
    	 */
    	@GetMapping("/admins/{mail}")
	    public ResponseEntity<AdminResponse> getAdminFromMail(@PathVariable("mail") String mail){
	        return adminService.getAdminFromMail(mail);
	    }
    	
    	/**
    	 * Gets the reports of admin.
    	 *
    	 * @return the reports
    	 */
    	@GetMapping("/admins/reports")
	    public ResponseEntity<List<AdminReportResponse>> getAdminReport(){
	        return adminService.getAdminReport();
	    }
    	
    	/**
    	 * send newsletter.
    	 *
    	 * @return how many mail are sended
    	 */
    	@GetMapping("/admins/newsletter")
	    public ResponseEntity<Integer> sendNewsletter(){
	        return adminService.sendNewsletter();
	    }
    	
    	/**
	     * Get managers by their request.
	     *
	     * @param active the active or not (the manager is active of not)
	     * @param scadute if the manager has to renew his activation
	     * @return all managers that has the selected request
	     */
    	@GetMapping("/admins/requests")
	    public ResponseEntity<List<ManagerPlusResponse>> getRequests(@RequestParam(defaultValue = "0") int active, @RequestParam(defaultValue = "0") int scadute){
    		return adminService.getRequest(active>0?true:false,scadute>0?true : false);
	    }
    	
    	/**
	     * Accept a request.
	     *
	     * @param id the id of manager to activate
	     * @return done or not
	     */
    	@GetMapping("/admins/accept/{id}")
	    public ResponseEntity<Boolean> setRequestActive(@PathVariable("id") String id){
    		return adminService.setRequestActive(id);
	    }
    	
    	/**
	     * Remove a request of a manager (and the manager ifself).
	     *
	     * @param id the id of manager to remove
	     * @return done or not
	     */
    	@DeleteMapping("/admins/remove/{id}")
	    public ResponseEntity<Boolean> removeRequest(@PathVariable("id") String id){
    		return adminService.removeRequest(id);
	    }
    	
    	/**
	     * Give a malus to the manager.
	     *
	     * @param id id of manager which should have the malus
	     * @return done or not
	     */
    	@GetMapping("/admins/malus={id}")
	    public ResponseEntity<Boolean> setRequestMalus(@PathVariable("id") String id){
    		return adminService.setRequestDateMalus(id);
	    }

	   
	    /**
    	 * Delete by id of admin.
    	 *
    	 * @param id the id of admin
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/admins/{id}")
	    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id){
	        return adminService.delete(id);
	    }

}
