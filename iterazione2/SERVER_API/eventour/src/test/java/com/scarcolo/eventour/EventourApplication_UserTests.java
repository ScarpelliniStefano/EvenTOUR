package com.scarcolo.eventour;



import org.json.JSONArray;
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
import com.scarcolo.eventour.model.AccountRequest;
import com.scarcolo.eventour.model.AccountResponse;
import com.scarcolo.eventour.model.Location;
import com.scarcolo.eventour.model.ticketinsp.AddTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.EditTicketInspRequest;
import com.scarcolo.eventour.model.ticketinsp.TicketInspResponse;
import com.scarcolo.eventour.model.user.AddUserRequest;
import com.scarcolo.eventour.model.user.EditUserRequest;
import com.scarcolo.eventour.model.user.UserResponse;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.LinkedHashMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_UserTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getEvenTour() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api//users/61a0a933bce0e98fbb2da18a/eventour/5",
				String.class);
		JSONArray arr=new JSONArray(request);
		assertEquals(arr.length(),5);
		
	}
	
	
	@Test
	public void getUserById() throws Exception {
		UserResponse request=this.restTemplate.getForObject("http://localhost:" + port + "/api/users/61a0a933bce0e98fbb2da18a",
				UserResponse.class);
		assertEquals(request.getId(),"61a0a933bce0e98fbb2da18a");
		assertEquals(request.getMail(),"Morgana_Battistini@libero.it");
		assertEquals(request.getSurname(),"Battistini");
		
	}
	
	@Test
	public void getUserByIdError() throws Exception {
		ResponseEntity<TicketInspResponse> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/ticketInsps/61a0a933bce0e98fbb2da184",
				TicketInspResponse.class);
		assertEquals(request.getStatusCode(),HttpStatus.NOT_FOUND);
		
	}
	
	@Test
	public void newUser() throws Exception {
		AddUserRequest request=new AddUserRequest();
		request.name="NomeProva";
		request.surname="CognomeProva";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.mail="provaUser@gmail.com";
		request.password="provaPsw";
		request.residence=new Location();
			request.residence.setLocality("prova via");
			request.residence.setCap("24042");
			request.residence.setRegione("Lombardia");
			request.residence.setProvincia("Bergamo");
			request.residence.setCity("Albino");
			request.residence.setLat(44f);
			request.residence.setLng(9f);
			request.residence.setSigla("BG");
		request.sex="M";
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.username="userProva";
		UserResponse userCreated = restTemplate.postForObject("http://localhost:" + port + "/api/users", request, UserResponse.class);
		assertEquals(userCreated.getMail(), "provaUser@gmail.com");
		String reqDel="http://localhost:" + port + "/api/users/"+userCreated.getId();
		restTemplate.delete(reqDel);
		

	}
	
	@Test
	public void updateUser() throws Exception {
		AddUserRequest request=new AddUserRequest();
		request.name="NomeProva";
		request.surname="CognomeProva";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.mail="provaUser@gmail.com";
		request.password="provaPsw";
		request.residence=new Location();
			request.residence.setLocality("prova via");
			request.residence.setCap("24042");
			request.residence.setRegione("Lombardia");
			request.residence.setProvincia("Bergamo");
			request.residence.setCity("Albino");
			request.residence.setLat(44f);
			request.residence.setLng(9f);
			request.residence.setSigla("BG");
		request.sex="M";
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.username="userProva";
		UserResponse userCreated = restTemplate.postForObject("http://localhost:" + port + "/api/users", request, UserResponse.class);
		assertEquals(userCreated.getMail(), "provaUser@gmail.com");
		
		
		EditUserRequest reqUpd=new EditUserRequest();
		reqUpd.id=userCreated.getId();
		reqUpd.residence=userCreated.getResidence();
		reqUpd.residence.setCap("24021");
		ResponseEntity<UserResponse> resp=restTemplate.exchange("http://localhost:" + port + "/api/users", HttpMethod.PUT, new HttpEntity<EditUserRequest>(reqUpd), UserResponse.class);
		assertEquals(resp.getStatusCodeValue(),200);
		assertEquals(resp.getBody().getId(),userCreated.getId());
		assertEquals(resp.getBody().getResidence().getCap(),"24021");
		String reqDel="http://localhost:" + port + "/api/users/"+userCreated.getId();
		restTemplate.delete(reqDel);
		
		reqUpd.id="61a0a933bce0e98fbb2da184";
		resp=restTemplate.exchange("http://localhost:" + port + "/api/ticketInsps", HttpMethod.PUT, new HttpEntity<EditUserRequest>(reqUpd), UserResponse.class);
		assertEquals(resp.getStatusCodeValue(),404);
		
		
	}
	
	@Test
	public void newUserError() throws Exception {
		AddUserRequest request=new AddUserRequest();
		request.name="NomeProva";
		request.surname="CognomeProva";
		request.dateOfBirth=LocalDate.now().plusYears(2);
		request.mail="provaUser@gmail.com";
		request.password="provaPsw";
		request.residence=new Location();
			request.residence.setLocality("prova via");
			request.residence.setCap("24021");
			request.residence.setRegione("Lombardia");
			request.residence.setProvincia("Bergamo");
			request.residence.setCity("Albino");
			request.residence.setLat(44f);
			request.residence.setLng(9f);
			request.residence.setSigla("BG");
		request.sex="M";
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.username="userProva";
		ResponseEntity<UserResponse> userCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/users", request, UserResponse.class);
		assertEquals(userCreated.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}
	
	@Test
	public void deleteUser() throws Exception {
		AddUserRequest request=new AddUserRequest();
		request.name="NomeProva";
		request.surname="CognomeProva";
		request.dateOfBirth=LocalDate.now().minusYears(30);
		request.mail="provaUser@gmail.com";
		request.password="provaPsw";
		request.residence=new Location();
			request.residence.setLocality("prova via");
			request.residence.setCap("24042");
			request.residence.setRegione("Lombardia");
			request.residence.setProvincia("Bergamo");
			request.residence.setCity("Albino");
			request.residence.setLat(44f);
			request.residence.setLng(9f);
			request.residence.setSigla("BG");
		request.sex="M";
		String[] tipi= {"1.2.2","2.2"};
		request.types=tipi;
		request.username="userProva";
		UserResponse userCreated = restTemplate.postForObject("http://localhost:" + port + "/api/users", request, UserResponse.class);
		assertEquals(userCreated.getMail(), "provaUser@gmail.com");
		String reqDel="http://localhost:" + port + "/api/users/"+userCreated.getId();
		ResponseEntity<Boolean> resp=restTemplate.exchange(reqDel, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		
	}
	
	@Test
	public void deleteUserNonCorrect() throws Exception {
		ResponseEntity<Boolean> resp=restTemplate.exchange("http://localhost:" + port + "/api/users/61a0a933bce0e98fbb2da184", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void getUserAll() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/users/",
				String.class);
		assertEquals(request.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void getAccountOfUser() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="Colombano72@hotmail.com";
		request.password=Functionalities.getMd5("yelukare");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"User");
		LinkedHashMap<String,String> user = new LinkedHashMap<>();
		user=LinkedHashMap.class.cast(response.getUser());
		//JSONObject jsonResp=new JSONObject(response.getUser().toString());
		//UserResponse user=(UserResponse)response.getUser();
		assertEquals(user.get("id"), "61a0a933bce0e98fbb2d999d");
	}
	
	@Test
	public void getAccountOfManager() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="Vera_Nucci@libero.it";
		request.password=Functionalities.getMd5("fanomuge");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"Manager");
		LinkedHashMap<String,String> user = new LinkedHashMap<>();
		user=LinkedHashMap.class.cast(response.getUser());
		//JSONObject jsonResp=new JSONObject(response.getUser().toString());
		//UserResponse user=(UserResponse)response.getUser();
		assertEquals(user.get("id"), "61a0a0eeb5f9b12d06e95240");
	}
	
	@Test
	public void getAccountOfTicket() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="TI-379-7253";
		request.password=Functionalities.getMd5("vosipowo");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"TicketInsp");
		LinkedHashMap<String,String> user = new LinkedHashMap<>();
		user=LinkedHashMap.class.cast(response.getUser());
		//JSONObject jsonResp=new JSONObject(response.getUser().toString());
		//UserResponse user=(UserResponse)response.getUser();
		assertEquals(user.get("id"), "61a0b05abce0e98fbb2dad3e");
	}
	
	@Test
	public void getAccountOfUserErr() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="Colombano74@hotmail.com";
		request.password=Functionalities.getMd5("yelukare");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"NONE");
		String stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. unregistered user");
		
		request=new AccountRequest();
		request.username="Colombano72@hotmail.com";
		request.password="yelukare2";
		response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"User");
		stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. invalid password");
	}
	
	@Test
	public void getAccountOfManagerErr() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="Vera_Nucci2@libero.it";
		request.password=Functionalities.getMd5("fanomuge");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"NONE");
		String stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. unregistered user");
		
		request=new AccountRequest();
		request.username="Vera_Nucci@libero.it";
		request.password="fanomuge2";
		response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"Manager");
		stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. invalid password");
	}
	
	@Test
	public void getAccountOfTicketErr() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="TI-379-7252";
		request.password=Functionalities.getMd5("vosipowo");
		AccountResponse response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"NONE");
		String stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. unregistered ticket inspector");
		
		request=new AccountRequest();
		request.username="TI-379-7253";
		request.password="vosipowo2";
		response=this.restTemplate.postForObject("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getTypeUser(),"TicketInsp");
		stringErr=String.class.cast(response.getUser());
		assertEquals(stringErr, "ERROR. invalid password");
	}
	
	@Test
	public void getAccountNoCompileErr() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="";
		request.password=Functionalities.getMd5("vosipowo");
		ResponseEntity<AccountResponse> response=this.restTemplate.postForEntity("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	

}
