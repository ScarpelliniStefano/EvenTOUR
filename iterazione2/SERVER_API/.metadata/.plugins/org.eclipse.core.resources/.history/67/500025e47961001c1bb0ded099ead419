package com.scarcolo.eventour.service.event;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.repository.event.EventRepository;
import com.scarcolo.eventour.repository.user.UserRepository;
import com.scarcolo.eventour.service.booking.BookingService;
import com.scarcolo.eventour.service.ticketisp.TicketInspService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// TODO: Auto-generated Javadoc
/**
 * The Class EventService.
 */
@Service
public class EventService {

    /** The event repository. */
    @Autowired
    private EventRepository eventRepository;
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /** The booking service. */
    @Autowired
    BookingService bookingService;
    
    /** The ticket service. */
    @Autowired
    TicketInspService ticketService;

   
    /**
     * Add a event.
     *
     * @param request the request of new event
     * @return the response entity with event created
     */
    public ResponseEntity<EventResponse> add(AddEventRequest request){
        Event event = null;
		try {
			event = eventRepository.save(new Event(request));
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

  
    /**
     * Update a event.
     *
     * @param request the request of update
     * @return the response entity with updated event
     */
    public ResponseEntity<EventResponse> update(EditEventRequest request){
        Optional<Event> optionalEvent = eventRepository.findById(request.id);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        Event e=optionalEvent.get();
        if(request.title!=null) {
        	e.setTitle(request.title);
        }
        if(request.description!=null) {
        	e.setDescription(request.description);
        }
        if(request.dataOra!=null) {
        	try {
				e.setDataOra(Functionalities.convertToDate(request.dataOra));
			} catch (Exception e1) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
        }
        if(request.location!=null) {
        	e.setLocation(request.location);
        }
        if(request.price!=null) {
        	e.setPrice(request.price);
        }
        if(request.totSeat!=null) {
        	e.setTotSeat(request.totSeat);
        }
        if(request.urlImage!=null) {
        	e.setUrlImage(request.urlImage);
        }
        if(request.types!=null) {
        	e.setTypes(request.types);
        }
        eventRepository.save(e);
        return new ResponseEntity<>(new EventResponse(e), HttpStatus.OK);
    }

   
    /**
     * Get a event by id.
     *
     * @param id the id
     * @return the event by id
     */
    public ResponseEntity<EventResponse> getById(String id) {
    	Optional<Event> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(new EventResponse(eventData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

    /**
     * Delete a event and all bookings and ticket inspectors.
     *
     * @param id the id of event
     * @return true, if successful
     */
    public boolean delete(String id) {
    	Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return false;
        }
        boolean resp=bookingService.deleteAllBookingFromEvent(optionalEvent.get());
        if(resp==false) {
        	System.out.println("error in deleting bookings");
        }
        resp=ticketService.deleteAllTicketsFromEvent(optionalEvent.get().getId());
        if(resp==false) {
        	System.out.println("error in deleting tickets");
        }
        eventRepository.deleteById(optionalEvent.get().getId());
        return true;
    }

    /**
	 * Get all events.
	 *
	 * @param page the page
	 * @param size the size
	 * @param ordered the order
	 * @param param the parameter to order on
	 * @return all events ordered, if necessary, and divided by page of size
	 */
	public ResponseEntity<Map<String, Object>> getAll(int page, int size, String ordered,String param) {
		try {
			List<Event> events = new ArrayList<>();
			
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			
			Page<Event> pageEvents;
			if(param==null)
					pageEvents=eventRepository.findAllFuture(paging);
			else {
				if(ordered.equalsIgnoreCase("desc")) {
					if(param.equals("dataOra")) {
						paging = PageRequest.of(page, size,Sort.by("dataOra").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else if(param.equals("loc")) {
						paging = PageRequest.of(page, size,Sort.by("location.regione","location.provincia","location.city").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else { 
						paging = PageRequest.of(page, size,Sort.by("dataOra").descending());
						pageEvents=eventRepository.findAllFuture(paging);
					}
				}else {
					if(param.equalsIgnoreCase("dataOra")) {
						paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else if(param.equals("loc")) {
						paging = PageRequest.of(page, size,Sort.by("location.regione","location.provincia","location.city").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}else {
						paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
						pageEvents=eventRepository.findAllFuture(paging);
					}
				}
			} 
			events=pageEvents.getContent();	
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
		    response.put("currentPage", pageEvents.getNumber());
		    response.put("totalItems", pageEvents.getTotalElements());
		    response.put("totalPages", pageEvents.getTotalPages());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets all events by data or interval of data.
	 *
	 * @param page the page
	 * @param size the size
	 * @param dataS the data string
	 * @return the events by data specified
	 */
	public ResponseEntity<Map<String, Object>> getByData(int page, int size, String dataS) {
		try {
			Date dataI,dataF;
			List<Event> events = new ArrayList<>(); 
            Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			Page<Event> pageEvents;
			if(dataS.contains(",")) {
				dataI=new SimpleDateFormat("yyyy-MM-dd").parse(dataS.split(",")[0]);
				dataF=new SimpleDateFormat("yyyy-MM-dd").parse(dataS.split(",")[1]);
				Calendar c = Calendar.getInstance(); 
			    c.setTime(dataF); 
			    c.add(Calendar.DATE, 1);
			    dataF = c.getTime();
				System.out.println(dataI+" "+dataF);
			}else {
				Date data=new SimpleDateFormat("yyyy-MM-dd").parse(dataS) ;
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    dataI = formatter.parse(formatter.format(data));
			    Calendar c = Calendar.getInstance(); 
			    c.setTime(dataI); 
			    c.add(Calendar.DATE, 1);
			    dataF = c.getTime();
			    dataF = formatter.parse(formatter.format(dataF));
			}
			
			pageEvents=eventRepository.findByDataOraBetweenOrderByDataOraAsc(dataI, dataF,paging);
			events=pageEvents.getContent();
	  	  if (events.isEmpty()) {
	  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  	  } 
	  	  List<EventResponse> eventR= new ArrayList<>();
		  for(Event event: events) eventR.add(new EventResponse(event));
		  Map<String, Object> response = new HashMap<>();
		  response.put("events", eventR);
		  response.put("currentPage", pageEvents.getNumber());
		  response.put("totalItems", pageEvents.getTotalElements());
		  response.put("totalPages", pageEvents.getTotalPages());
		  return new ResponseEntity<>(response, HttpStatus.OK);
	  	  
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Gets all events by location.
	 *
	 * @param page the page
	 * @param size the size
	 * @param loc the location name
	 * @param type the type of location (regione, provincia, city)
	 * @return the all events by location
	 */
	public ResponseEntity<Map<String, Object>> getByLoc(int page, int size, String loc, String type) {
		try {
			List<Event> events = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			
			Page<Event> pageEvents;
			if(type.equalsIgnoreCase("regione")) {
				pageEvents = eventRepository.findByLocationLike("location.regione",loc,paging);
			}else if(type.equalsIgnoreCase("provincia")) {
				pageEvents = eventRepository.findByLocationLike("location.provincia",loc,paging);
			}else if(type.equalsIgnoreCase("city")) {
				pageEvents = eventRepository.findByLocationLike("location.city",loc,paging);
			}else {
				pageEvents=eventRepository.findAll(paging);
			}
			events=pageEvents.getContent();
		  	if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	} 
		  	List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
			response.put("currentPage", pageEvents.getNumber());
			response.put("totalItems", pageEvents.getTotalElements());
			response.put("totalPages", pageEvents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		  	  
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Gets all events by preferences.
	 *
	 * @param page the page
	 * @param size the size
	 * @param idPref the id of user
	 * @param locInclude true if location is included in filter the events
	 * @return the events by preferences
	 */
	public ResponseEntity<Map<String, Object>> getByPreferences(int page, int size, String idPref, boolean locInclude) {
		Optional<User> userData = userRepository.findById(idPref);

	  	  if (userData.isPresent()) {
	  		  if(!locInclude)
	  			  return this.getByTypes(page, size, Arrays.toString(userData.get().getTypes()).replace(" ", "").replace("[", "").replace("]", ""));
	  		  else
	  			  return this.getByTypesWithLoc(page, size, userData.get().getTypes(),userData.get().getResidence().getRegione());
	  	  } else {
	  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  	  }
		
	}


	/**
	 * Gets all events by types with location filtered.
	 *
	 * @param page the page
	 * @param size the size
	 * @param types the types
	 * @param region the region to witch filter
	 * @return all event by types with location
	 */
	private ResponseEntity<Map<String, Object>> getByTypesWithLoc(int page, int size, String[] types, String region) {
		try {
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			Page<Event> pageEvents;
			pageEvents = eventRepository.findByTypesAndLocation_RegioneLike(types,region,paging);
			List<Event> events=pageEvents.getContent();
		  	  if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	  } 
		  	  List<EventResponse> eventR= new ArrayList<>();
			  for(Event event: events) eventR.add(new EventResponse(event));
			  Map<String, Object> response = new HashMap<>();
			  response.put("events", eventR);
			  response.put("currentPage", pageEvents.getNumber());
			  response.put("totalItems", pageEvents.getTotalElements());
			  response.put("totalPages", pageEvents.getTotalPages());
			  return new ResponseEntity<>(response, HttpStatus.OK);
			  
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


	/**
	 * Gets all events by types.
	 *
	 * @param page the page
	 * @param size the size
	 * @param type the types array
	 * @return all event of a given types
	 */
	public ResponseEntity<Map<String, Object>> getByTypes(int page, int size, String type) {
		try {
			String[] types=type.split(",");
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			
			Page<Event> pageEvents;
			pageEvents = eventRepository.findByTypes(types,paging);
			List<Event> events=pageEvents.getContent();
		  	  if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	  } 
		  	  List<EventResponse> eventR= new ArrayList<>();
			  for(Event event: events) eventR.add(new EventResponse(event));
			  Map<String, Object> response = new HashMap<>();
			  response.put("events", eventR);
			  response.put("currentPage", pageEvents.getNumber());
			  response.put("totalItems", pageEvents.getTotalElements());
			  response.put("totalPages", pageEvents.getTotalPages());
			  return new ResponseEntity<>(response, HttpStatus.OK);
			  
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


	/**
	 * Gets all events by id manager.
	 *
	 * @param page the page
	 * @param size the size
	 * @param id the id manager
	 * @return page required of size with the events
	 */
	public ResponseEntity<Map<String, Object>> getByIdMan(int page, int size, String id) {
		try {
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			Page<Event> pageEvents;
			pageEvents=eventRepository.findByManagerId(new ObjectId(id),paging);
			List<Event> events=pageEvents.getContent();
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) {
				eventR.add(new EventResponse(event));
			}
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
			response.put("currentPage", pageEvents.getNumber());
			response.put("totalItems", pageEvents.getTotalElements());
			response.put("totalPages", pageEvents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Gets all events with free seats.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the events with free seats
	 */
	public ResponseEntity<Map<String, Object>> getEventsDisp(int page, int size) {
		try {
			
			Sort sort = Sort.by("dataOra").ascending().and(Sort.by("freeSeat").ascending());
			Pageable paging = PageRequest.of(page, size,sort);
			Page<Event> pageEvents;
			pageEvents = eventRepository.findByFreeSeatGreaterThanZero(paging);
			List<Event> events=pageEvents.getContent();
		  	if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	} 
		  	List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			Map<String, Object> response = new HashMap<>();
			response.put("events", eventR);
			response.put("currentPage", pageEvents.getNumber());
			response.put("totalItems", pageEvents.getTotalElements());
			response.put("totalPages", pageEvents.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		  	  
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


	

	  /*
	   * API OF EVENTS WITHOUT PAGE AND SIZE, BUT WITH SAME SIGNATURE
	   *
	public ResponseEntity<List<EventResponse>> getAll(String ordered, String param) {
		try {
			List<Event> events = new ArrayList<>();

			if(param==null)
					eventRepository.findAll().forEach(events::add);
			else {
				if(ordered.equalsIgnoreCase("desc")) {
					if(param.equals("dataOra")) {
						eventRepository.findAll(Sort.by("dataOra").descending()).forEach(events::add);
					}else if(param.equals("loc")) {
						eventRepository.findAll(Sort.by("location.regione","location.provincia","location.city").descending()).forEach(events::add);
					}else { 
						eventRepository.findAll(Sort.by("dataOra").descending()).forEach(events::add);
					}
				}else {
					if(param.equalsIgnoreCase("dataOra")) {
						eventRepository.findAll(Sort.by("dataOra").ascending()).forEach(events::add);
					}else if(param.equals("loc")) {
						eventRepository.findAll(Sort.by("location.regione","location.provincia","location.city").ascending()).forEach(events::add);
					}else {
						eventRepository.findAll(Sort.by("dataOra").ascending()).forEach(events::add);
					}
				}
			} 
			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<List<EventResponse>> getByData(String dataS) {
		try {
			Date dataI,dataF;
			List<Event> events = new ArrayList<>(); 

			if(dataS.contains(",")) {
				dataI=new SimpleDateFormat("yyyy-MM-dd").parse(dataS.split(",")[0]);
				dataF=new SimpleDateFormat("yyyy-MM-dd").parse(dataS.split(",")[1]);
			}else {
				Date data=new SimpleDateFormat("yyyy-MM-dd").parse(dataS) ;
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    dataI = formatter.parse(formatter.format(data));
			    Calendar c = Calendar.getInstance(); 
			    c.setTime(dataI); 
			    c.add(Calendar.DATE, 1);
			    dataF = c.getTime();
			    dataF = formatter.parse(formatter.format(dataF));
			}
			
			eventRepository.findByDataOraBetweenOrderByDataOraAsc(dataI, dataF).forEach(events::add);

	  	  if (events.isEmpty()) {
	  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  	  } 
	  	  List<EventResponse> eventR= new ArrayList<>();
		  for(Event event: events) eventR.add(new EventResponse(event));
		  return new ResponseEntity<>(eventR, HttpStatus.OK);
	  	  
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<List<EventResponse>> getByLoc(String loc, String type) {
		try {
			List<Event> events = new ArrayList<>();
			if(type.equalsIgnoreCase("regione")) {
				events = eventRepository.findByLocationLike("location.regione",loc);
			}else if(type.equalsIgnoreCase("provincia")) {
				events = eventRepository.findByLocationLike("location.provincia",loc);
			}else if(type.equalsIgnoreCase("city")) {
				events = eventRepository.findByLocationLike("location.city",loc);
			}else {
				System.out.println("else");
				eventRepository.findAll().forEach(events::add);
			}
		  	if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	} 
		  	List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) eventR.add(new EventResponse(event));
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		  	  
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<List<EventResponse>> getByTypes(String type) {
		try {
			String[] types=type.split(",");
			List<Event> events = eventRepository.findByTypes(types);

		  	  if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	  } 
		  	  List<EventResponse> eventR= new ArrayList<>();
			  for(Event event: events) eventR.add(new EventResponse(event));
			  return new ResponseEntity<>(eventR, HttpStatus.OK);
		  	  
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


	public ResponseEntity<List<EventResponse>> getByPreferences(String pref) {
		return this.getByTypes(pref);
	}


	public ResponseEntity<List<EventResponse>> getByIdMan(String id) {
		try {

			List<Event> events=eventRepository.findByManagerId(id);

			if(events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<EventResponse> eventR= new ArrayList<>();
			for(Event event: events) {
				eventR.add(new EventResponse(event));
			}
			return new ResponseEntity<>(eventR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<List<EventResponse>> getEventsDisp() {
		try {
			Sort sort = Sort.by("freeSeat").ascending().and(Sort.by("title").ascending());
			List<Event> events = eventRepository.findByfreeSeatGreaterThanZero(sort);
		  	  if (events.isEmpty()) {
		  	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  	  } 
		  	  List<EventResponse> eventR= new ArrayList<>();
			  for(Event event: events) eventR.add(new EventResponse(event));
			  return new ResponseEntity<>(eventR, HttpStatus.OK);
		  	  
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	*/


	
}
