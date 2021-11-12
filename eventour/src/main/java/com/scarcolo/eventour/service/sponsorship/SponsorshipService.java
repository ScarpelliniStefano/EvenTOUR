package com.scarcolo.eventour.service.sponsorship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.sponsorship.AddSponsorshipRequest;
import com.scarcolo.eventour.model.sponsorship.EditSponsorshipRequest;
import com.scarcolo.eventour.model.sponsorship.Sponsorship;
import com.scarcolo.eventour.repository.sponsorship.SponsorshipRepository;

@Service
public class SponsorshipService {
	
	@Autowired
    private SponsorshipRepository sponsorshipRepository;

   
    public ResponseEntity<Sponsorship> add(AddSponsorshipRequest request) {
        Sponsorship event = sponsorshipRepository.save(new Sponsorship(request));
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

  
    public ResponseEntity<Sponsorship> update(EditSponsorshipRequest request) {
        Optional<Sponsorship> optionalEvent = sponsorshipRepository.findById(request.id);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
    }

   
    public ResponseEntity<Sponsorship> getById(String id) {
    	Optional<Sponsorship> eventData = sponsorshipRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    public boolean delete(String id) {
        Optional<Sponsorship> optionalEvent = sponsorshipRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return false;
        }
        sponsorshipRepository.deleteById(optionalEvent.get().getId().toString());
        return true;
    }

	public ResponseEntity<List<Sponsorship>> getAll() {
		try {
			List<Sponsorship> events = new ArrayList<>();
			sponsorshipRepository.findAll().forEach(events::add);
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
