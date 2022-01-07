package com.scarcolo.eventour;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EditEventRequest;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_EventTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getEventsWithSize25() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events?size=25",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		JSONArray events = obj.getJSONArray("events");
		assertEquals(events.length(),25);
	}
	
	@Test
	public void getEventsWithDateInterval() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/data=2022-05-01,2022-08-01",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		JSONArray events = obj.getJSONArray("events");
		LocalDateTime dataI=Functionalities.convertToLocalDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-01"));
		LocalDateTime dataF=Functionalities.convertToLocalDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-01"));
		for(int i=0;i<events.length();i++) {
			assertTrue(LocalDateTime.parse(new JSONObject(events.get(i).toString()).getString("dataOra")).isAfter(dataI) && 
					LocalDateTime.parse(new JSONObject(events.get(i).toString()).getString("dataOra")).isBefore(dataF));
		}
		}
	
	@Test
	public void getEventsWithDate() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/data=2022-05-01",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		JSONArray events = obj.getJSONArray("events");
		LocalDateTime dataI=Functionalities.convertToLocalDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-01"));
		LocalDateTime dataF=Functionalities.convertToLocalDateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-02"));
		for(int i=0;i<events.length();i++) {
			assertTrue(LocalDateTime.parse(new JSONObject(events.get(i).toString()).getString("dataOra")).isAfter(dataI) && 
					LocalDateTime.parse(new JSONObject(events.get(i).toString()).getString("dataOra")).isBefore(dataF));
		}
		}
	
	@Test
	public void getEventsWithLoc() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/loc=provincia/Bergamo",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		JSONArray events = obj.getJSONArray("events");
		for(int i=0;i<events.length();i++) 
			assertEquals(new JSONObject(new JSONObject(events.get(i).toString()).getString("location")).getString("provincia"),"Bergamo");
		
		}
	
	@Test
	public void getEventsWithType() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/type=1.1.1,2.2",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		JSONArray events = obj.getJSONArray("events");
		for(int i=0;i<events.length();i++) {
			JSONArray arr=new JSONArray(new JSONObject(events.get(i).toString()).getString("types"));
			boolean findCorrect=false;
			for(int j=0;j<arr.length() && !findCorrect;j++) {
				if(arr.getString(j).contentEquals("1.1.1") || arr.getString(j).contentEquals("2.2")) findCorrect=true;
			}
			assertEquals(findCorrect,true);
		}
		}
	
	@Test
	public void getEventsWithPref() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events/pref/61a0a933bce0e98fbb2d999d",
				String.class);
			assertEquals(request.getStatusCodeValue(),200);
		
		}
	
	@Test
	public void getEventsWithPrefLoc() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events/pref/61a0a933bce0e98fbb2d999d?locInclude=true",
				String.class);
			assertEquals(request.getStatusCodeValue(),200);
		
		}
	
	@Test
	public void getEventsDisp() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events/disp",
				String.class);
			assertEquals(request.getStatusCodeValue(),200);
			JSONObject obj=new JSONObject(request.getBody());
			
			for(int i=0;i<obj.getJSONArray("events").length();i++) {
				assertTrue(obj.getJSONArray("events").getJSONObject(i).getInt("freeSeat")>0);
			}
		
		}
	
	
	
	@Test
	public void getEventsPage3() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events?page=3",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		int pageActual = obj.getInt("currentPage");
		assertEquals(pageActual,3);
		JSONArray events = obj.getJSONArray("events");
		assertEquals(events.length(),20);
	}
	
	@Test
	public void getEventsDescNotOrdered() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events?ordered=desc&param=loc",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		int pageActual = obj.getInt("currentPage");
		assertEquals(pageActual,0);
		JSONArray events = obj.getJSONArray("events");
		assertEquals(events.length(),20);
	}
	
	@Test
	public void getEventsPageError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events?size=2000&page=1000",
				String.class);
		
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,204);
	}
	
	@Test
	public void getEventByIdCorrect() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/61a0a85ebce0e98fbb2d860f",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		String stringTitle = obj.getString("title");
		assertEquals(stringTitle,"EVENTO 1");
	}
	
	@Test
	public void getEventByIdError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events/61a0a85ebce0e98fbb2d860d",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,404);
	}
	
	@Test
	public void newEvent() throws Exception {
		AddEventRequest request=new AddEventRequest();
		request.title="eventProva";
		request.description="prova";
		request.dataOra=LocalDateTime.now().plusDays(3);
		request.location=new Location();
				request.location.setLocality("prova via");
				request.location.setCap("24042");
				request.location.setRegione("Lombardia");
				request.location.setProvincia("Bergamo");
				request.location.setCity("Albino");
				request.location.setLat(44d);
				request.location.setLng(9d);
				request.location.setSigla("BG");
		request.managerId="61a0a0eeb5f9b12d06e9523a";
		request.price=2.0d;
		request.totSeat=80;
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.urlImage="https://fakeimg.pl/300/";
		EventResponse eventCreated = restTemplate.postForObject("http://localhost:" + port + "/api/events", request, EventResponse.class);
		assertEquals(eventCreated.getTitle(), "eventProva");
		
		restTemplate.delete("http://localhost:" + port + "/api/events/"+eventCreated.id);
		
	}
	
	@Test
	public void deleteEventWithBookingAndTicket() throws Exception {
		AddEventRequest request=new AddEventRequest();
		request.title="eventProva";
		request.description="prova";
		request.dataOra=LocalDateTime.now().plusDays(3);
		request.location=new Location();
				request.location.setLocality("prova via");
				request.location.setCap("24042");
				request.location.setRegione("Lombardia");
				request.location.setProvincia("Bergamo");
				request.location.setCity("Albino");
				request.location.setLat(44d);
				request.location.setLng(9d);
				request.location.setSigla("BG");
		request.managerId="61a0a0eeb5f9b12d06e9523a";
		request.price=2.0d;
		request.totSeat=80;
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.urlImage="https://fakeimg.pl/300/";
		EventResponse eventCreated = restTemplate.postForObject("http://localhost:" + port + "/api/events", request, EventResponse.class);
		AddTicketInspRequest requestTick=new AddTicketInspRequest();
		requestTick.eventId=eventCreated.id;
		requestTick.fullName="Ticket Prova";
		TicketInspResponse ticketCreated = restTemplate.postForObject("http://localhost:" + port + "/api/ticketInsps", requestTick, TicketInspResponse.class);
		AddBookingRequest requestBook=new AddBookingRequest();
		requestBook.eventId=eventCreated.id;
		requestBook.userId="61a0a933bce0e98fbb2d999d";
		requestBook.prenotedSeat=3;
		String bookingCreated = restTemplate.postForObject("http://localhost:" + port + "/api/bookings", requestBook, String.class);
		JSONObject obj = new JSONObject(bookingCreated);
		String reqDel="http://localhost:" + port + "/api/events/"+eventCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(reqDel, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		ResponseEntity<String> requestBookDel=restTemplate.getForEntity("http://localhost:" + port + "/api/bookings/"+obj.getString("id"),
				String.class);
		int statusCode=requestBookDel.getStatusCodeValue();
		assertEquals(statusCode,404);
		ResponseEntity<TicketInspResponse> requestTickDel=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/"+ticketCreated.getId(),
				TicketInspResponse.class);
		assertEquals(requestTickDel.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void deleteEventWithoutBookingAndTicket() throws Exception {
		AddEventRequest request=new AddEventRequest();
		request.title="eventProva";
		request.description="prova";
		request.dataOra=LocalDateTime.now().plusDays(3);
		request.location=new Location();
				request.location.setLocality("prova via");
				request.location.setCap("24042");
				request.location.setRegione("Lombardia");
				request.location.setProvincia("Bergamo");
				request.location.setCity("Albino");
				request.location.setLat(44d);
				request.location.setLng(9d);
				request.location.setSigla("BG");
		request.managerId="61a0a0eeb5f9b12d06e9523a";
		request.price=2.0d;
		request.totSeat=80;
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.urlImage="https://fakeimg.pl/300/";
		EventResponse eventCreated = restTemplate.postForObject("http://localhost:" + port + "/api/events", request, EventResponse.class);
		String reqDel="http://localhost:" + port + "/api/events/"+eventCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(reqDel, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
	}
	
	@Test
	public void updateEvent() throws Exception {
		AddEventRequest request=new AddEventRequest();
		request.title="eventProva";
		request.description="prova";
		request.dataOra=LocalDateTime.now().plusDays(3);
		request.location=new Location();
				request.location.setLocality("prova via");
				request.location.setCap("24042");
				request.location.setRegione("Lombardia");
				request.location.setProvincia("Bergamo");
				request.location.setCity("Albino");
				request.location.setLat(44d);
				request.location.setLng(9d);
				request.location.setSigla("BG");
		request.managerId="61a0a0eeb5f9b12d06e9523a";
		request.price=2.0d;
		request.totSeat=80;
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.urlImage="https://fakeimg.pl/300/";
		EventResponse eventCreated = restTemplate.postForObject("http://localhost:" + port + "/api/events", request, EventResponse.class);
		EditEventRequest reqUpd=new EditEventRequest();
		reqUpd.id=eventCreated.id;
		reqUpd.description="prova aggiornata";
		reqUpd.dataOra=LocalDateTime.now().plusDays(4);
        reqUpd.title="eventProvaUpd";
        reqUpd.location=request.location;
        reqUpd.location.setCap("24021");
        reqUpd.price=3.0d;
        reqUpd.totSeat=100;
        reqUpd.urlImage="https://fakeimg.pl/400/";
        String[] tipiUpd= {"1.1.2","3.2"};
        reqUpd.types=tipiUpd;
		ResponseEntity<EventResponse> resp=restTemplate.exchange("http://localhost:" + port + "/api/events", HttpMethod.PUT, new HttpEntity<EditEventRequest>(reqUpd), EventResponse.class);
		assertEquals(resp.getStatusCodeValue(),200);
		assertEquals(resp.getBody().description,"prova aggiornata");
		assertNotEquals(resp.getBody().dataOra,eventCreated.dataOra);
		restTemplate.delete("http://localhost:" + port + "/api/events/"+eventCreated.id);
		
	}
	
	@Test
	public void newEventError() throws Exception {
		AddEventRequest request=new AddEventRequest();
		request.title="eventProva";
		request.description="prova";
		request.dataOra=LocalDateTime.now().minusDays(3);
		request.location=new Location();
				request.location.setLocality("prova via");
				request.location.setCap("24042");
				request.location.setRegione("Lombardia");
				request.location.setProvincia("Bergamo");
				request.location.setCity("Albino");
				request.location.setLat(44d);
				request.location.setLng(9d);
				request.location.setSigla("BG");
		request.managerId="61a0a0eeb5f9b12d06e9523a";
		request.price=2.0d;
		request.totSeat=80;
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.urlImage="https://fakeimg.pl/300/";
		ResponseEntity<EventResponse> eventCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/events", request, EventResponse.class);
		assertEquals(eventCreated.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}

	@Test
	public void getEventByIdManCorrect() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/events/manager/61a0a0eeb5f9b12d06e9523a",
				String.class);
		
		JSONObject obj = new JSONObject(request);
		for(int i=0;i<obj.getJSONArray("events").length();i++) {
			assertEquals(obj.getJSONArray("events").getJSONObject(i).getString("managerId"),"61a0a0eeb5f9b12d06e9523a");
		}
		
	}
	
	@Test
	public void getEventByIdManError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/events/manager/61a0a0eeb5f9b12d06e95236",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,204);
	}
	

}
