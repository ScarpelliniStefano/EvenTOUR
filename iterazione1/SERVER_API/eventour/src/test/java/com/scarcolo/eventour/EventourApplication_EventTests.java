package com.scarcolo.eventour;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EventResponse;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;

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
				request.location.setLat(44f);
				request.location.setLng(9f);
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
				request.location.setLat(44f);
				request.location.setLng(9f);
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
