package com.scarcolo.eventour;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scarcolo.eventour.model.AccountRequest;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.event.AddEventRequest;
import com.scarcolo.eventour.model.event.EventResponse;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.User;
import com.scarcolo.eventour.model.user.UserResponse;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_TicketTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getTicket() throws Exception {
		TicketInspResponse request=this.restTemplate.getForObject("http://localhost:" + port + "/api/ticketInsps/61a0b05abce0e98fbb2dad3e",
				TicketInspResponse.class);
		assertEquals(request.getCode(),"TI-379-7253");
		assertEquals(request.getEventId(),"61a0a85ebce0e98fbb2d8ec8");
		assertEquals(request.getFullName(),"Magno Filice");
		assertEquals(request.getPassword(),"vosipowo");
		
	}
	
	@Test
	public void getTicketError() throws Exception {
		ResponseEntity<TicketInspResponse> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/61a0b05abce0e98fbb2dad3b",
				TicketInspResponse.class);
		assertEquals(request.getStatusCode(),HttpStatus.NOT_FOUND);
		
	}
	
	@Test
	public void getTicketByEvent() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/ticketInsps/event/61a0a85ebce0e98fbb2d9117",
				String.class);
		JSONArray array=new JSONArray(request);
		for(int i=0;i<array.length();i++) {
			assertEquals(array.getJSONObject(i).getString("eventId"), "61a0a85ebce0e98fbb2d9117");
		}
		
	}
	
	@Test
	public void getTicketByEventError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/event/61a0a85ebce0e98fbb2d9116",
				String.class);
		assertEquals(request.getStatusCode(), HttpStatus.NO_CONTENT);
		
	}
	

}
