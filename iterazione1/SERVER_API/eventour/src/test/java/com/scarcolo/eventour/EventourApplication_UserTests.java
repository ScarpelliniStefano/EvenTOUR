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
import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_UserTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAccountOfUser() throws Exception {
		AccountRequest request=new AccountRequest();
		request.username="Colombano72@hotmail.com";
		request.password="yelukare";
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
		request.password="fanomuge";
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
		request.password="vosipowo";
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
		request.password="yelukare";
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
		request.password="fanomuge";
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
		request.password="vosipowo";
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
		request.password="vosipowo";
		ResponseEntity<AccountResponse> response=this.restTemplate.postForEntity("http://localhost:" + port + "/api/account", request, 
				AccountResponse.class);
		assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	

}
