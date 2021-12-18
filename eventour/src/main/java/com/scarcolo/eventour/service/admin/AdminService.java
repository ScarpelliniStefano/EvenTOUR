package com.scarcolo.eventour.service.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import com.scarcolo.eventour.model.event.EventPlus;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.model.user.UserBookedResponse;
import com.scarcolo.eventour.repository.admin.AdminRepository;
import com.scarcolo.eventour.repository.booking.BookingRepository;
import com.scarcolo.eventour.repository.manager.ManagerRepository;
import com.scarcolo.eventour.service.manager.ManagerService;
import com.scarcolo.eventour.service.manager.RequestService;
import com.scarcolo.eventour.service.user.UserService;

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
	
	
	public ResponseEntity<List<AdminReportResponse>> getAdminReport() {
		try {
			AggregationResults<ReportAdmResponse> reportA=managerRepository.findReports();
			List<ReportAdmResponse> reportMongo=reportA.getMappedResults();
			List<AdminReportResponse> reportR=new ArrayList<>();
			
			Integer numEventi=0;
			Integer numFuturi=0;
			Double mediaComes=0d;
			Double rating=0d;
			
			for(ReportAdmResponse resp : reportMongo) {
				if(resp.getRequest()[0].isActive()) {
					if(resp.getEvent()!=null) {
						numEventi=resp.getEvent().length;
						numFuturi=Functionalities.dataFutura(resp.getEvent());
						mediaComes=0d;
						rating=0d;
						for(EventPlus event: resp.getEvent()) {
							if(event.getDataOra().isBefore(LocalDateTime.now())) {
								mediaComes+=(event.getTotSeat()-event.getFreeSeat())*1.0d/event.getTotSeat();
								rating=(event.getReviewSum()*1.0d)/event.getReviewTot();
							}
							
						}
						rating=rating/(numEventi-numFuturi);
						mediaComes=100*mediaComes/(numEventi-numFuturi);
					}
					Manager manager=new Manager(resp.getId(),resp.getName(), resp.getSurname(), resp.getMail(),resp.getCodicePIVA(),resp.getDateOfBirth(),
							"", resp.getRagioneSociale(), resp.getResidence());
					reportR.add(new AdminReportResponse(resp.getId(), resp.getCodicePIVA(), new ManagerPlusResponse(manager,resp.getRequest()[0]), numEventi, numFuturi, mediaComes, rating));
				
				}
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
	
	@Autowired
	private RequestService requestService;

	public ResponseEntity<List<ManagerPlusResponse>> getRequest(boolean active,boolean scadute) {
		try {
			List<ManagerPlusResponse> reqManager=requestService.getAll(active,scadute);
			return new ResponseEntity<>(reqManager, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Boolean> setRequestActive(String id) {
		try {
			Request reqManager=requestService.getById(id);
			if(reqManager!=null) {
				reqManager.setActive(!reqManager.isActive());
				this.setRequestDate(reqManager);
				requestService.update(reqManager);
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Boolean setRequestDate(Request reqManager) {
		if(reqManager!=null) {
			reqManager.setDateRenewal(Functionalities.convertToDate(LocalDate.now()));
			requestService.update(reqManager);
			return true;
		}
		return false;
	}
	
	public ResponseEntity<Boolean> setRequestDateMalus(String id) {
		try {
			Request reqManager=requestService.getById(id);
			if(reqManager!=null) {
				reqManager.setDateRenewal(Functionalities.convertToDate(LocalDate.now().minusYears(1).atStartOfDay()));
				requestService.update(reqManager);
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Autowired
	private UserService userService;

	public ResponseEntity<Integer> sendNewsletter() {
			Integer newsGood=userService.sendNews();
			if(newsGood!=null) {
				return new ResponseEntity<>(newsGood, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Autowired
	private ManagerService managerService;

	public ResponseEntity<Boolean> removeRequest(String id) {
		try {
			Request reqManager=requestService.getById(id);
			if(reqManager!=null) {
				managerService.delete(id);
				requestService.delete(id);
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
