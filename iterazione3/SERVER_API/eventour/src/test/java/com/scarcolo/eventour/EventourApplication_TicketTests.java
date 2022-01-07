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

import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import static org.junit.Assert.assertEquals;

import org.json.JSONArray;

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
	
	@Test
	public void newTicket() throws Exception {
		AddTicketInspRequest request=new AddTicketInspRequest();
		request.eventId="61a0a85ebce0e98fbb2d862b";
		request.fullName="Utente Prova";
		TicketInspResponse ticketCreated = restTemplate.postForObject("http://localhost:" + port + "/api/ticketInsps", request, TicketInspResponse.class);
		assertEquals(ticketCreated.getEventId(), "61a0a85ebce0e98fbb2d862b");
		String reqDel="http://localhost:" + port + "/api/ticketInsps/"+ticketCreated.getId();
		restTemplate.delete(reqDel);
		

	}
	
	@Test
	public void updateTicket() throws Exception {
		AddTicketInspRequest request=new AddTicketInspRequest();
		request.eventId="61a0a85ebce0e98fbb2d862b";
		request.fullName="Utente Prova";
		TicketInspResponse ticketCreated = restTemplate.postForObject("http://localhost:" + port + "/api/ticketInsps", request, TicketInspResponse.class);
		
		EditTicketInspRequest reqUpd=new EditTicketInspRequest();
		reqUpd.id=ticketCreated.getId();
		reqUpd.fullName="Utente Aggiornato";
		ResponseEntity<TicketInspResponse> resp=restTemplate.exchange("http://localhost:" + port + "/api/ticketInsps", HttpMethod.PUT, new HttpEntity<EditTicketInspRequest>(reqUpd), TicketInspResponse.class);
		assertEquals(resp.getStatusCodeValue(),200);
		assertEquals(resp.getBody().getCode(),ticketCreated.getCode());
		assertEquals(resp.getBody().getEventId(),"61a0a85ebce0e98fbb2d862b");
		assertEquals(resp.getBody().getFullName(),"Utente Aggiornato");
		assertEquals(resp.getBody().getPassword(),ticketCreated.getPassword());
		String reqDel="http://localhost:" + port + "/api/ticketInsps/"+ticketCreated.getId();
		restTemplate.delete(reqDel);
		
		reqUpd.id="61a89029facdaf0af830be5a";
		resp=restTemplate.exchange("http://localhost:" + port + "/api/ticketInsps", HttpMethod.PUT, new HttpEntity<EditTicketInspRequest>(reqUpd), TicketInspResponse.class);
		assertEquals(resp.getStatusCodeValue(),404);
		
		
	}
	
	@Test
	public void newTicketError() throws Exception {
		AddTicketInspRequest request=new AddTicketInspRequest();
		request.eventId="61a0a85ebce0e98fbb2d860a";
		request.fullName="Utente Prova";
		ResponseEntity<TicketInspResponse> ticketCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/ticketInsps", request, TicketInspResponse.class);
		assertEquals(ticketCreated.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}
	
	@Test
	public void deleteTicket() throws Exception {
		AddTicketInspRequest request=new AddTicketInspRequest();
		request.eventId="61a0a85ebce0e98fbb2d862b";
		request.fullName="Utente Prova";
		TicketInspResponse ticketCreated = restTemplate.postForObject("http://localhost:" + port + "/api/ticketInsps", request, TicketInspResponse.class);
		String reqDel="http://localhost:" + port + "/api/ticketInsps/"+ticketCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(reqDel, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		
	}
	
	@Test
	public void deleteTicketNonCorrect() throws Exception {
		ResponseEntity<Boolean> resp=restTemplate.exchange("http://localhost:" + port + "/api/ticketInsps/61a89029facdaf0af830be5a", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getTicketAll() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
	}
	
	///ticketInsps/manager/{id}
	@Test
	public void getTicketByManager() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/ticketInsps/manager/61a0a0eeb5f9b12d06e9525e",
				String.class);
		JSONArray array=new JSONArray(request);
		System.out.println(array);
		assertEquals(array.getJSONObject(0).getString("mail"), "Celinia.Casagrande36@libero.it");
		
	}
	
	@Test
	public void getTicketByManagerError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/manager/61a0a0eeb5f9b12d06e95235",
				String.class);
		assertEquals(request.getStatusCode(), HttpStatus.NO_CONTENT);
		
	}

}
