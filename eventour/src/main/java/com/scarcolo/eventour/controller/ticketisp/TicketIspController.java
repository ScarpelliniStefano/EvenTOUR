package com.scarcolo.eventour.controller.ticketisp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.ticketisp.AddTicketIspRequest;
import com.scarcolo.eventour.model.ticketisp.EditTicketIspRequest;
import com.scarcolo.eventour.model.ticketisp.TicketIsp;
import com.scarcolo.eventour.service.ticketisp.TicketIspService;

@RestController
@RequestMapping("/api")
public class TicketIspController {
	
	@Autowired
	private TicketIspService eventService;
	 @PostMapping("/ticketisp")
	    public ResponseEntity<TicketIsp> addTicketIsp(@RequestBody AddTicketIspRequest request) throws Exception{
	      return eventService.add(request);
	    }

	   
	    @PutMapping("/ticketisp")
	    public ResponseEntity<TicketIsp> updateTicketIsp(@RequestBody EditTicketIspRequest request){
	        return eventService.update(request);
	    }


	   
	    @GetMapping("/ticketisp/{id}")
	    public ResponseEntity<TicketIsp> getTicketIspById(@PathVariable("id") String id){
	        return eventService.getById(id);
	    }
	    

	    @GetMapping("/ticketisp")
	    public ResponseEntity<List<TicketIsp>> getAllTicketIsps(){
	        return eventService.getAll();
	    }

	   
	    @DeleteMapping("/ticketisp/{id}")
	    public boolean deleteById(@RequestParam String id){
	        return eventService.delete(id);
	    }
	
}
