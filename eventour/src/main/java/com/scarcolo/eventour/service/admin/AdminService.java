package com.scarcolo.eventour.service.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.admin.Admin;
import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.admin.AdminReportResponse;
import com.scarcolo.eventour.model.admin.ReportAdmResponse;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.model.user.UserBookedResponse;
import com.scarcolo.eventour.repository.admin.AdminRepository;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.service.manager.RequestService;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminService.
 */
@Service
public class AdminService {
	
	/** The admin repository. */
	@Autowired
	private AdminRepository adminRepository;
	
	/** The manager repository. */
	@Autowired
	private ManagerRepository managerRepository;
   
	 /**
 	 * Gets the manager by id.
 	 *
 	 * @param id the id
 	 * @return the manager by id
 	 */
 	public ResponseEntity<AdminResponse> getById(String id){
	    	Optional<Admin> adminData = adminRepository.findById(id);

	  	  if (adminData.isPresent()) {
	  	    return new ResponseEntity<>(new AdminResponse(adminData.get()), HttpStatus.OK);
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
	 }
  
    /**
     * Delete a admin.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isEmpty()) {
            return false;
        }
        adminRepository.deleteById(optionalAdmin.get().getId().toString());
        return true;
    }

	/**
	 * Gets all admins.
	 *
	 * @return all admins
	 */
	public ResponseEntity<List<AdminResponse>> getAll() {
		try {
			List<Admin> admins = new ArrayList<>();
			adminRepository.findAll().forEach(admins::add);
			if(admins.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<AdminResponse> adminR= new ArrayList<>();
			for(Admin admin: admins) adminR.add(new AdminResponse(admin));
			return new ResponseEntity<>(adminR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Autowired
	BookingRepository bookingRepository;
	
	public ResponseEntity<List<AdminReportResponse>> getAdminReport() {
		try {
			AggregationResults<ReportAdmResponse> reportA=managerRepository.findReports();
			List<ReportAdmResponse> reportMongo=reportA.getMappedResults();
			List<AdminReportResponse> reportR=new ArrayList<>();
			
			Integer numEventi=0;
			Integer numFuturi=0;
			Double mediaComes=0d;
			Double mediaPrenotati=0d;
			Double rating=0d;
			List<UserBookedResponse> books=null;

			for(ReportAdmResponse resp : reportMongo) {
				numEventi=resp.getEvent().length;
				numFuturi=Functionalities.dataFutura(resp.getEvent());
				mediaComes=0d;
				mediaPrenotati=0d;
				rating=0d;
				for(Event event: resp.getEvent()) {
					if(event.getDataOra().isBefore(LocalDateTime.now())) {
						mediaComes+=(100*(event.getTotSeat()-event.getFreeSeat()/event.getTotSeat()));
						books=bookingRepository.findByEventId(new ObjectId(event.getId())).getMappedResults();
						for(UserBookedResponse book : books)
							rating+=book.getReview()>0 ? book.getReview() : 0;
					}else
						mediaPrenotati+=(100*(event.getTotSeat()-event.getFreeSeat()/event.getTotSeat()));
				}
				mediaComes=mediaComes/(numEventi-numFuturi);
				mediaPrenotati=mediaPrenotati/numFuturi;
				rating=rating/(numEventi-numFuturi);
				Manager manager=new Manager(resp.getId(),resp.getName(), resp.getSurname(), resp.getMail(),resp.getCodicePIVA(),Functionalities.convertToDate(resp.getDateOfBirth()),
											null, resp.getRagioneSociale(), resp.getResidence());
				reportR.add(new AdminReportResponse(resp.getId(), resp.getCodicePIVA(), new ManagerPlusResponse(manager,resp.getRequest()[0]), numEventi, numFuturi, mediaComes, mediaPrenotati, rating));
			}
			
			return new ResponseEntity<>(reportR, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<AdminResponse> getAdminFromMail(String mail) {
		List<Admin> adminData = adminRepository.findByMail(mail);

	  	  if (!adminData.isEmpty()) {
	  	    return new ResponseEntity<>(new AdminResponse(adminData.get(0)), HttpStatus.OK);
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
	}




}
