package com.scarcolo.eventour.controller.sponsorship;

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

import com.scarcolo.eventour.model.sponsorship.AddSponsorshipRequest;
import com.scarcolo.eventour.model.sponsorship.EditSponsorshipRequest;
import com.scarcolo.eventour.model.sponsorship.Sponsorship;
import com.scarcolo.eventour.service.sponsorship.SponsorshipService;

// TODO: Auto-generated Javadoc
/**
 * The Class SponsorshipController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SponsorshipController {

	/** The sponsorship service. */
	@Autowired
	private SponsorshipService sponsorshipService;
	 
 	/**
 	 * Adds the sponsorship.
 	 *
 	 * @param request the request
 	 * @return the response entity
 	 */
 	@PostMapping("/sponsorship")
	    public ResponseEntity<Sponsorship> addSponsorship(@RequestBody AddSponsorshipRequest request){
	      return sponsorshipService.add(request);
	    }

	   
	    /**
    	 * Update sponsorship.
    	 *
    	 * @param request the request
    	 * @return the response entity
    	 */
    	@PutMapping("/sponsorship")
	    public ResponseEntity<Sponsorship> updateSponsorship(@RequestBody EditSponsorshipRequest request){
	        return sponsorshipService.update(request);
	    }


	   
	    /**
    	 * Gets the sponsorship by id.
    	 *
    	 * @param id the id
    	 * @return the sponsorship by id
    	 */
    	@GetMapping("/sponsorship/{id}")
	    public ResponseEntity<Sponsorship> getSponsorshipById(@PathVariable("id") String id){
	        return sponsorshipService.getById(id);
	    }
	    

	    /**
    	 * Gets all sponsorships.
    	 *
    	 * @return the all sponsorship
    	 */
    	@GetMapping("/sponsorship")
	    public ResponseEntity<List<Sponsorship>> getAllSponsorships(){
	        return sponsorshipService.getAll();
	    }

	   
	    /**
    	 * Delete by id.
    	 *
    	 * @param id the id
    	 * @return true, if successful
    	 */
    	@DeleteMapping("/sponsorship/{id}")
	    public boolean deleteById(@RequestParam String id){
	        return sponsorshipService.delete(id);
	    }

	
}
