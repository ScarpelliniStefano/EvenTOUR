package com.scarcolo.eventour;



import org.json.JSONArray;
import org.json.JSONObject;
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

import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.EventReportResponse;
import com.scarcolo.eventour.model.manager.ManagerResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_ManagerTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void newManager() throws Exception {
		AddManagerRequest request=new AddManagerRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="prova@gmail.com";
		request.password="provaUser";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.residence=new Location();
				request.residence.setLocality("prova via");
				request.residence.setCap("24042");
				request.residence.setRegione("Lombardia");
				request.residence.setProvincia("Bergamo");
				request.residence.setCity("Albino");
				request.residence.setLat(44f);
				request.residence.setLng(9f);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		assertEquals(managerCreated.getCodicePIVA(), "77914680192");
		
		String reqDel="http://localhost:" + port + "/api/managers/"+managerCreated.getId();
		restTemplate.delete(reqDel);
		

	}
	
	@Test
	public void updateManager() throws Exception {
		AddManagerRequest request=new AddManagerRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="prova@gmail.com";
		request.password="provaUser";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.residence=new Location();
				request.residence.setLocality("prova via");
				request.residence.setCap("24042");
				request.residence.setRegione("Lombardia");
				request.residence.setProvincia("Bergamo");
				request.residence.setCity("Albino");
				request.residence.setLat(44f);
				request.residence.setLng(9f);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		EditManagerRequest reqUpd=new EditManagerRequest();
		reqUpd.id=managerCreated.getId();
		reqUpd.residence=managerCreated.getResidence();
		reqUpd.residence.setCap("24021");
		reqUpd.mail="provaUpd@gmail.com";
		ResponseEntity<ManagerResponse> resp=restTemplate.exchange("http://localhost:" + port + "/api/managers", HttpMethod.PUT, new HttpEntity<EditManagerRequest>(reqUpd), ManagerResponse.class);
		assertEquals(resp.getStatusCodeValue(),200);
		assertEquals(resp.getBody().getMail(),"provaUpd@gmail.com");
		assertEquals(resp.getBody().getResidence().getCap(),"24021");
		String reqDel="http://localhost:" + port + "/api/managers/"+managerCreated.getId();
		restTemplate.delete(reqDel);
		
	}
	
	@Test
	public void newManagerError() throws Exception {
		AddManagerRequest request=new AddManagerRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="prova@gmail.com";
		request.password="provaUser";
		request.dateOfBirth=LocalDate.now().plusYears(5);
		request.residence=new Location();
				request.residence.setLocality("prova via");
				request.residence.setCap("24042");
				request.residence.setRegione("Lombardia");
				request.residence.setProvincia("Bergamo");
				request.residence.setCity("Albino");
				request.residence.setLat(44f);
				request.residence.setLng(9f);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ResponseEntity<ManagerResponse> managerCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		assertEquals(managerCreated.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}
	
	@Test
	public void deleteManager() throws Exception {
		AddManagerRequest request=new AddManagerRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="prova@gmail.com";
		request.password="provaUser";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.residence=new Location();
				request.residence.setLocality("prova via");
				request.residence.setCap("24042");
				request.residence.setRegione("Lombardia");
				request.residence.setProvincia("Bergamo");
				request.residence.setCity("Albino");
				request.residence.setLat(44f);
				request.residence.setLng(9f);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		String delMan="http://localhost:" + port + "/api/managers/"+managerCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(delMan, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		
	}
	
	@Test
	public void deleteManagerNonCorrect() throws Exception {
		ResponseEntity<Boolean> resp=restTemplate.exchange("http://localhost:" + port + "/api/managers/61a0a0eeb5f9b12d06e95236", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getManagerById() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/managers/61a0a0eeb5f9b12d06e9523a",
				String.class);
		JSONObject obj = new JSONObject(request);
		String stringId = obj.getString("id");
		assertEquals(stringId,"61a0a0eeb5f9b12d06e9523a");
		assertEquals(obj.getString("mail"),"Beniamina73@yahoo.it");
		assertEquals(obj.getString("codicePIVA"),"074859517");
	}
	
	@Test
	public void getManagerByIdError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/managers/61a0a0eeb5f9b12d06e95236",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,404);
		
	}
	
	//@GetMapping("/managers/{id}/reports")
	
	@Test
	public void getManagerReportById() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/managers/61a0a0eeb5f9b12d06e9523a/reports",
				String.class);
		JSONArray obj = new JSONArray(request);
		for(int i=0;i<obj.length();i++) {
			String stringId = obj.getJSONObject(i).getJSONObject("eventDetails").getString("managerId");
			assertEquals(stringId,"61a0a0eeb5f9b12d06e9523a");
		}
	}
	
	@Test
	public void getManagerReportByIdError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/managers/61a0a0eeb5f9b12d06e95236/reports",
				String.class);
		assertEquals(request.getStatusCodeValue(),404);
		
	}
	
	@Test
	public void getManagerAll() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/managers/",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
	}
	

}
