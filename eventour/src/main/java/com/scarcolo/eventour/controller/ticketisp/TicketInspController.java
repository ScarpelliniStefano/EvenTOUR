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
import org.springframework.web.bind.annotation.RestController;

import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInsp;
import com.scarcolo.eventour.service.ticketisp.TicketInspService;


@RestController
@RequestMapping("/api")
public class TicketInspController {
	
	@Autowired
	private TicketInspService ticketInspService;
	 
	@PostMapping("/ticketInsps")
	public ResponseEntity<TicketInsp> addTicketInsp(@RequestBody AddTicketInspRequest request) throws Exception{
	      return ticketInspService.add(request);
	}

	   
	    @PutMapping("/ticketInsps")
	    public ResponseEntity<TicketInsp> updateTicketInsp(@RequestBody EditTicketInspRequest request){
	        return ticketInspService.update(request);
	    }


	   
	    @GetMapping("/ticketInsps/{id}")
	    public ResponseEntity<TicketInsp> getTicketInspById(@PathVariable("id") String id){
	        return ticketInspService.getById(id);
	    }
	    

	    @GetMapping("/ticketInsps")
	    public ResponseEntity<List<TicketInsp>> getAllTicketInsps(){
	        return ticketInspService.getAll();
	    }

	   
	    @DeleteMapping("/ticketInsps/{id}")
	    public boolean deleteTicketInspById(@PathVariable("id") String id){
	        return ticketInspService.delete(id);
	    }

}
