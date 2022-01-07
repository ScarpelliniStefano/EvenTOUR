package com.scarcolo.eventour.service.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.functions.Mail;
import com.scarcolo.eventour.model.admin.AddAdminRequest;
import com.scarcolo.eventour.model.admin.Admin;
import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.admin.EditAdminRequest;
import com.scarcolo.eventour.model.admin.AdminReportResponse;
import com.scarcolo.eventour.model.admin.ReportAdmResponse;
import com.scarcolo.eventour.model.event.EventPlus;
import com.scarcolo.eventour.model.manager.Manager;
import com.scarcolo.eventour.model.manager.ManagerPlusResponse;
import com.scarcolo.eventour.model.request.Request;
import com.scarcolo.eventour.repository.admin.AdminRepository;
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
	
	/** The manager service. */
	@Autowired
	private ManagerService managerService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The request service. */
	@Autowired
	private RequestService requestService;
	
	
	/**
	 * Add a admin.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	public ResponseEntity<AdminResponse> add(AddAdminRequest request){
		Admin admin=null;
		try {
			admin = adminRepository.save(new Admin(request));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	     return new ResponseEntity<>(new AdminResponse(admin), HttpStatus.OK);
	}

	/**
	 * Update a admin.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	public ResponseEntity<AdminResponse> update(EditAdminRequest request) {
		Optional<Admin> optionalAdmin = adminRepository.findById(request.id);
        if (optionalAdmin.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Admin a=optionalAdmin.get();
        if(request.name!=null) {
        	a.setName(request.name);
        }
        if(request.surname!=null) {
        	a.setSurname(request.surname);
        }
        if(request.password!=null) {
        	a.setPassword(request.password);
        }
        if(request.role!=null) {
        	a.setRole(request.role);
        }
        
        adminRepository.save(a);
        return new ResponseEntity<>(new AdminResponse(a), HttpStatus.OK);
	}
   
	 /**
 	 * Gets the admin by id.
 	 *
 	 * @param id the id
 	 * @return the admin by id
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
    public ResponseEntity<Boolean> delete(String id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        adminRepository.deleteById(optionalAdmin.get().getId().toString());
        return new ResponseEntity<>(true,HttpStatus.OK);
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Gets the admin report.
	 *
	 * @return the admin report
	 */
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
				if(resp.getRequest().length>0) {
					if(resp.getRequest()[0].isActive()) {
						
						if(resp.getEvent()!=null && resp.getEvent().length>0) {
							numEventi=resp.getEvent().length;
							numFuturi=Functionalities.dataFutura(resp.getEvent());
							mediaComes=0d;
							rating=0d;
							int cont=0;
							for(EventPlus event: resp.getEvent()) {
								if(event.getDataOra().isBefore(LocalDateTime.now())) {
									mediaComes+=(event.getTotSeat()-event.getFreeSeat())*1.0d/event.getTotSeat();
									if(event.getReviewTot()>0) {
										rating+=(event.getReviewSum()*1.0d)/event.getReviewTot();
										cont++;
									}
								}
								
							}
							
							rating=rating/cont;
							mediaComes=100*mediaComes/(numEventi-numFuturi);
						}
						
						Manager manager=new Manager(resp.getId(),resp.getName(), resp.getSurname(), resp.getMail(),resp.getCodicePIVA(),resp.getDateOfBirth(),
								"", resp.getRagioneSociale(), resp.getResidence());
						reportR.add(new AdminReportResponse(resp.getId(), resp.getCodicePIVA(), new ManagerPlusResponse(manager,resp.getRequest()[0]), numEventi, numFuturi, mediaComes, rating));
					
					}
				}
			}
			return new ResponseEntity<>(reportR, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the admin from mail.
	 *
	 * @param mail the mail
	 * @return the admin from mail
	 */
	public ResponseEntity<AdminResponse> getAdminFromMail(String mail) {
		if(!Functionalities.isValidEmailAddress(mail)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<Admin> adminData = adminRepository.findByMail(mail);

	  	  if (!adminData.isEmpty()) {
	  	    return new ResponseEntity<>(new AdminResponse(adminData.get(0)), HttpStatus.OK);
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
	}
	


	/**
	 * Get managers by their request.
	 *
	 * @param active the active or not (the manager is active of not)
	 * @param scadute if the manager has to renew his activation
	 * @return all managers that has the selected request
	 */
	public ResponseEntity<List<ManagerPlusResponse>> getRequest(boolean active,boolean scadute) {
		try {
			List<ManagerPlusResponse> reqManager=requestService.getAll(active,scadute);
			return new ResponseEntity<>(reqManager, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Sets the request active.
	 *
	 * @param id the id of manager to activate
	 * @return the response entity with done or not
	 */
	public ResponseEntity<Boolean> setRequestActive(String id) {
		try {
			Optional<Manager> man=managerRepository.findById(id);
			if(man.isPresent()) {
				Request reqManager=requestService.getById(id);
				if(reqManager!=null) {
					reqManager.setActive(!reqManager.isActive());
					this.setRequestDate(reqManager);
					requestService.update(reqManager);
					Mail.sendAcceptManagerMsg(man.get().getMail());
					return new ResponseEntity<>(true, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Sets the request date renewal to today.
	 *
	 * @param reqManager the request of manager to which change date renewal
	 * @return the boolean of done or not
	 */
	private Boolean setRequestDate(Request reqManager) {
		if(reqManager!=null) {
			reqManager.setDateRenewal(Functionalities.convertToDate(LocalDate.now()));
			requestService.update(reqManager);
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the request date renewal applying a malus.
	 *
	 * @param id the id of manager to which apply the malus
	 * @return the response entity of done or not
	 */
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


	/**
	 * Send newsletter.
	 *
	 * @return the response entity with how many newsletter mail are sended
	 */
	public ResponseEntity<Integer> sendNewsletter() {
			Integer newsGood=userService.sendNews();
			if(newsGood!=null) {
				return new ResponseEntity<>(newsGood, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	

	/**
	 * Remove the request and the associated manager.
	 *
	 * @param id the id of manager
	 * @return the response entity of done or not
	 */
	public ResponseEntity<Boolean> removeRequest(String id) {
		try {
			Optional<Manager> man=managerRepository.findById(id);
			if(man.isPresent()) {
				Request reqManager=requestService.getById(id);
				if(reqManager!=null) {
					Mail.sendRefuseManagerMsg(man.get().getMail());
					managerService.delete(id);
					requestService.delete(id);
					return new ResponseEntity<>(true, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	




}
