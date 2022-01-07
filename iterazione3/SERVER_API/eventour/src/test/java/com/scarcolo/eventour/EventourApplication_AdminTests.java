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

import com.scarcolo.eventour.functions.Functionalities;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.admin.AddAdminRequest;
import com.scarcolo.eventour.model.admin.AdminResponse;
import com.scarcolo.eventour.model.admin.EditAdminRequest;
import com.scarcolo.eventour.model.manager.AddManagerRequest;
import com.scarcolo.eventour.model.manager.EditManagerRequest;
import com.scarcolo.eventour.model.manager.ManagerResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_AdminTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void newAdmin() throws Exception {
		AddAdminRequest request=new AddAdminRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="provaAdmin@gmail.com";
		request.password="provaUser";
		request.role="helper";
		AdminResponse adminCreated = restTemplate.postForObject("http://localhost:" + port + "/api/admins", request, AdminResponse.class);
		assertEquals(adminCreated.getMail(), "provaAdmin@gmail.com");
		
		String reqDel="http://localhost:" + port + "/api/admins/"+adminCreated.getId();
		restTemplate.delete(reqDel);
		

	}
	
	@Test
	public void updateAdmin() throws Exception {
		AddAdminRequest request=new AddAdminRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="provaAdmin@gmail.com";
		request.password="provaUser";
		request.role="helper";
		AdminResponse adminCreated = restTemplate.postForObject("http://localhost:" + port + "/api/admins", request, AdminResponse.class);
		EditAdminRequest reqUpd=new EditAdminRequest();
		reqUpd.id=adminCreated.getId();
		reqUpd.role="moderator";
		ResponseEntity<AdminResponse> resp=restTemplate.exchange("http://localhost:" + port + "/api/admins", HttpMethod.PUT, new HttpEntity<EditAdminRequest>(reqUpd), AdminResponse.class);
		assertEquals(resp.getStatusCodeValue(),200);
		assertEquals(resp.getBody().getMail(),"provaAdmin@gmail.com");
		assertEquals(resp.getBody().getRole(),"MODERATOR");
		String reqDel="http://localhost:" + port + "/api/admins/"+adminCreated.getId();
		restTemplate.delete(reqDel);
		
	}
	
	@Test
	public void deleteAdmin() throws Exception {
		AddAdminRequest request=new AddAdminRequest();
		request.name="utenteNomeProva";
		request.surname="utenteCognomeProva";
		request.mail="provaAdmin@gmail.com";
		request.password="provaUser";
		request.role="helper";
		AdminResponse adminCreated = restTemplate.postForObject("http://localhost:" + port + "/api/admins", request, AdminResponse.class);
		String delMan="http://localhost:" + port + "/api/admins/"+adminCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(delMan, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		
	}
	
	@Test
	public void deleteAdminNonCorrect() throws Exception {
		ResponseEntity<Boolean> resp=restTemplate.exchange("http://localhost:" + port + "/api/admins/618a43a5885c9f6aab4af98a", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}


	@Test
	public void getAdminById() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/admins/id=618a43a5885c9f6aab4af98b",
				String.class);
		JSONObject obj = new JSONObject(request);
		String stringId = obj.getString("id");
		assertEquals(stringId,"618a43a5885c9f6aab4af98b");
		assertEquals(obj.getString("mail"),"admin@gmail.com");
		assertEquals(obj.getString("name"),"Administrator");
	}
	
	@Test
	public void getAdminByIdError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/id=618a43a5885c9f6aab4af98a",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,404);
		
	}
	
	@Test
	public void getAdminReport() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/reports",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void getAdminAll() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
		JSONArray reqArr=new JSONArray(request.getBody());
		assertEquals(reqArr.length(),2);
	}
	
	@Test
	public void getAdminMail() throws Exception {
		ResponseEntity<AdminResponse> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/admin@gmail.com",
				AdminResponse.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
		assertEquals(request.getBody().getId(),"618a43a5885c9f6aab4af98b");
	}
	
	@Test
	public void getAdminMailNonPresent() throws Exception {
		ResponseEntity<AdminResponse> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/adminNonPres@gmail.com",
				AdminResponse.class);
		assertEquals(request.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void getAdminMailError() throws Exception {
		ResponseEntity<AdminResponse> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/admingmail.com",
				AdminResponse.class);
		assertEquals(request.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getAdminNewsletter() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/newsletter",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void getAdminRequests() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/requests",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
		JSONArray arr=new JSONArray(request.getBody());
		for(int i=0;i<arr.length();i++) {
			assertEquals(arr.getJSONObject(i).getBoolean("active"),false);
		}
	}
	
	@Test
	public void getAdminAccept() throws Exception {
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
				request.residence.setLat(44d);
				request.residence.setLng(9d);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		ResponseEntity<String> requestNonActive=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/requests",
				String.class);
		JSONArray arr=new JSONArray(requestNonActive.getBody());
		Boolean finded=false;
		
		for(int i=0;i<arr.length() && finded==false;i++) {
			if(arr.getJSONObject(i).getString("id").contentEquals(managerCreated.getId())) finded=true;
		}
		assertEquals(finded,true);
		ResponseEntity<Boolean> requestAccept=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/accept/"+managerCreated.getId(),
				Boolean.class);
		assertEquals(requestAccept.getStatusCode(),HttpStatus.OK);
		assertEquals(requestAccept.getBody(),true);
		ResponseEntity<String> requestActive=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/requests?active=1",
				String.class);
		arr=new JSONArray(requestActive.getBody());
		finded=false;
		for(int i=0;i<arr.length() && finded==false;i++) {
			if(arr.getJSONObject(i).getString("id").contentEquals(managerCreated.getId())) finded=true;
		}
		assertEquals(finded,true);
		
		String reqDel="http://localhost:" + port + "/api/managers/"+managerCreated.getId();
		restTemplate.delete(reqDel);
	}
	
	@Test
	public void getAdminRemove() throws Exception {
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
				request.residence.setLat(44d);
				request.residence.setLng(9d);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		ResponseEntity<String> requestNonActive=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/requests",
				String.class);
		JSONArray arr=new JSONArray(requestNonActive.getBody());
		Boolean finded=false;
		
		for(int i=0;i<arr.length() && finded==false;i++) {
			if(arr.getJSONObject(i).getString("id").contentEquals(managerCreated.getId())) finded=true;
		}
		assertEquals(finded,true);
		ResponseEntity<Boolean> requestRemove=this.restTemplate.exchange("http://localhost:" + port + "/api/admins/remove/"+managerCreated.getId(), HttpMethod.DELETE, null, Boolean.class);
		assertEquals(requestRemove.getStatusCode(),HttpStatus.OK);
		assertEquals(requestRemove.getBody(),true);
		ResponseEntity<String> requestManager=this.restTemplate.getForEntity("http://localhost:" + port + "/api/managers/"+managerCreated.getId(),
				String.class);
		int statusCode=requestManager.getStatusCodeValue();
		assertEquals(statusCode,404);
	}
	
	@Test
	public void getAdminRemoveError() throws Exception {
		ResponseEntity<Boolean> requestRemove=this.restTemplate.exchange("http://localhost:" + port + "/api/admins/remove/61a0a0eeb5f9b12d06e95235", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(requestRemove.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void getAdminMalus() throws Exception {
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
				request.residence.setLat(44d);
				request.residence.setLng(9d);
				request.residence.setSigla("BG");
		request.codicePIVA="77914680192";
		request.ragioneSociale="prova s.r.l.";
		ManagerResponse managerCreated = restTemplate.postForObject("http://localhost:" + port + "/api/managers", request, ManagerResponse.class);
		this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/accept/"+managerCreated.getId(),
				Boolean.class);
		ResponseEntity<Boolean> requestMalus=this.restTemplate.getForEntity("http://localhost:" + port + "/api//admins/malus="+managerCreated.getId(),
				Boolean.class);
		assertEquals(requestMalus.getStatusCode(),HttpStatus.OK);
		assertEquals(requestMalus.getBody(),true);
		
		ResponseEntity<String> requestActive=this.restTemplate.getForEntity("http://localhost:" + port + "/api/admins/requests?active=1",
				String.class);
		JSONArray arr=new JSONArray(requestActive.getBody());
		int finded=-1;
		for(int i=0;i<arr.length() && finded==-1;i++) {
			if(arr.getJSONObject(i).getString("id").contentEquals(managerCreated.getId())) finded=i;
		}
		String dt=arr.getJSONObject(finded).getString("dateRenewal");
		String day=dt.substring(0, dt.indexOf("T"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(formatter.parse(day).before(Functionalities.convertToDate(LocalDate.now().minusYears(1))));
		
		String reqDel="http://localhost:" + port + "/api/managers/"+managerCreated.getId();
		restTemplate.delete(reqDel);
	}

}
