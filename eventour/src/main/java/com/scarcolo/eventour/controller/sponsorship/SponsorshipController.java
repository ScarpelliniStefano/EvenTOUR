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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SponsorshipController {

	@Autowired
	private SponsorshipService eventService;
	 @PostMapping("/sponsorship")
	    public ResponseEntity<Sponsorship> addEvent(@RequestBody AddSponsorshipRequest request){
	      return eventService.add(request);
	    }

	   
	    @PutMapping("/sponsorship")
	    public ResponseEntity<Sponsorship> updateEvent(@RequestBody EditSponsorshipRequest request){
	        return eventService.update(request);
	    }


	   
	    @GetMapping("/sponsorship/{id}")
	    public ResponseEntity<Sponsorship> getEventById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    @GetMapping("/sponsorship")
	    public ResponseEntity<List<Sponsorship>> getAllEvents(){
	        return eventService.getAll();
	    }

	   
	    @DeleteMapping("/sponsorship/{id}")
	    public boolean deleteById(@RequestParam String id){
	        return eventService.delete(id);
	    }

	
}
