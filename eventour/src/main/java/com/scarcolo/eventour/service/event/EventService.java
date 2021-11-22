package com.scarcolo.eventour.service.event;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.repository.event.EventRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

   
    public ResponseEntity<EventResponse> add(AddEventRequest request) throws Exception {
        Event event = eventRepository.save(new Event(request));
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

  
    /*public ResponseEntity<EventResponse> update(EditEventRequest request) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(request.id);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        return new ResponseEntity<>(new EventResponse(optionalEvent.get()), HttpStatus.OK);
    }*/

   
    public ResponseEntity<EventResponse> getById(String id) {
    	Optional<Event> eventData = eventRepository.findById(id);

  	  if (eventData.isPresent()) {
  	    return new ResponseEntity<>(new EventResponse(eventData.get()), HttpStatus.OK);
  	  } else {
  	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  	  }
    }

  
    /*public boolean delete(String id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return false;
        }
        eventRepository.deleteById(optionalEvent.get().getId());
        return true;
    }*/

	public ResponseEntity<Map<String, Object>> getAll(int page, int size, String ordered,String param) {
		try {
			List<Event> events = new ArrayList<>();
			
			Pageable paging = PageRequest.of(page, size);
			
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

	public ResponseEntity<Map<String, Object>> getByData(int page, int size, String dataS) {
		try {
			Date dataI,dataF;
			List<Event> events = new ArrayList<>(); 
            Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			Page<Event> pageEvents;
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


	public ResponseEntity<Map<String, Object>> getByLoc(int page, int size, String loc, String type) {
		try {
			List<Event> events = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size,Sort.by("dataOra").ascending());
			
			Page<Event> pageEvents;
			if(type.equalsIgnoreCase("regione")) {
				pageEvents = eventRepository.findByLocation_RegioneLike(loc,paging);
			}else if(type.equalsIgnoreCase("provincia")) {
				pageEvents = eventRepository.findByLocation_ProvinciaLike(loc,paging);
			}else if(type.equalsIgnoreCase("city")) {
				pageEvents = eventRepository.findByLocation_CityLike(loc,paging);
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


	public ResponseEntity<Map<String, Object>> getByPreferences(int page, int size, String pref) {
		return this.getByTypes(page, size, pref);
	}


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


	public ResponseEntity<Map<String, Object>> getEventsDisp(int page, int size) {
		try {
			
			Sort sort = Sort.by("dataOra").ascending().and(Sort.by("freeSeat").ascending());
			Pageable paging = PageRequest.of(page, size,sort);
			Page<Event> pageEvents;
			pageEvents = eventRepository.findByfreeSeatGreaterThanZero(paging);
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


	

/*
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
				events = eventRepository.findByLocation_RegioneLike(loc);
			}else if(type.equalsIgnoreCase("provincia")) {
				events = eventRepository.findByLocation_ProvinciaLike(loc);
			}else if(type.equalsIgnoreCase("city")) {
				events = eventRepository.findByLocation_CityLike(loc);
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
