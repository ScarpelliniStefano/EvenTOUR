package controller.sponsorship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.sponsorship.Sponsorship;
import repository.sponsorship.SponsorshipRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SponsorshipController {
	
	@Autowired
	SponsorshipRepository sponsorshipRepository;
	
	@GetMapping("/sponsors")
	public ResponseEntity<List<Sponsorship>> getAllSponsor(){
		try {
			List<Sponsorship> sponsors = new ArrayList<>();
			sponsorshipRepository.findAll().forEach(sponsors::add);
			if(sponsors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(sponsors, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/sponsors/{id}")
	public ResponseEntity<Sponsorship> getSponsorById(@PathVariable("id") String id) {
	  Optional<Sponsorship> sponsorData = sponsorshipRepository.findById(id);

	  if (sponsorData.isPresent()) {
	    return new ResponseEntity<>(sponsorData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
}
