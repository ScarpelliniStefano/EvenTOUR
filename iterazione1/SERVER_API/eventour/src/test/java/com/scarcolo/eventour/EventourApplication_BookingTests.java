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

import com.scarcolo.eventour.model.booking.AddBookingRequest;
import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.booking.CheckBookingRequest;

import static org.junit.Assert.assertEquals;
import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventourApplication_BookingTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getBookingByIdUserEventCorrect() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/bookings/user/61a0a933bce0e98fbb2da2a7/event/61a88e8efacdaf0af830bd2c",
				String.class);
		JSONArray obj = new JSONArray(request);
		String stringId = obj.getJSONObject(0).getString("id");
		assertEquals(stringId,"61a8c480648e0931525c2697");
	}
	
	@Test
	public void getBookingByIdUserEventError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/bookings/user/61a0a933bce0e98fbb2da2a7/event/61a88e8efacdaf0af830bd2b",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,405);
	}
	
	@Test
	public void getBookingByIdUserCorrect() throws Exception {
		String request=this.restTemplate.getForObject("http://localhost:" + port + "/api/bookings/user/61a0a933bce0e98fbb2da2a7",
				String.class);
		
		JSONArray obj = new JSONArray(request);
		for(int i=0;i<obj.length();i++) {
			assertEquals(obj.getJSONObject(i).getString("userId"),"61a0a933bce0e98fbb2da2a7");
		}
	}
	
	@Test
	public void getBookingByIdUserError() throws Exception {
		ResponseEntity<String> request=this.restTemplate.getForEntity("http://localhost:" + port + "/api/bookings/user/61a0a933bce0e98fbb2d999c",
				String.class);
		int statusCode=request.getStatusCodeValue();
		assertEquals(statusCode,400);
		
	}
	
	@Test
	public void newBooking() throws Exception {
		AddBookingRequest request=new AddBookingRequest();
		request.eventId="61a0a85ebce0e98fbb2d860f";
		request.userId="61a0a933bce0e98fbb2d999d";
		request.prenotedSeat=3;
		String bookingCreated = restTemplate.postForObject("http://localhost:" + port + "/api/bookings", request, String.class);
		JSONObject obj = new JSONObject(bookingCreated);
		assertEquals(obj.getString("userId"), "61a0a933bce0e98fbb2d999d");
		assertEquals(obj.getString("eventId"), "61a0a85ebce0e98fbb2d860f");
		restTemplate.delete("http://localhost:" + port + "/api/bookings/"+obj.getString("id"));
		
	}
	
	@Test
	public void newBookingError() throws Exception {
		AddBookingRequest request=new AddBookingRequest();
		request.eventId="61a0a85ebce0e98fbb2d860e";
		request.userId="61a0a933bce0e98fbb2d999d";
		request.prenotedSeat=3;
		ResponseEntity<String> bookingCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/bookings", request, String.class);
		assertEquals(bookingCreated.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
		
		
	}
	
	
	@Test
	public void deleteBooking() throws Exception {
		AddBookingRequest request=new AddBookingRequest();
		request.eventId="61a0a85ebce0e98fbb2d860f";
		request.userId="61a0a933bce0e98fbb2d999d";
		request.prenotedSeat=3;
		String bookingCreated = restTemplate.postForObject("http://localhost:" + port + "/api/bookings", request, String.class);
		JSONObject obj = new JSONObject(bookingCreated);
		String delBook="http://localhost:" + port + "/api/bookings/"+obj.getString("id");
		ResponseEntity<Boolean> resp=restTemplate.exchange(delBook, HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),true);
		ResponseEntity<Booking> requestControl=this.restTemplate.getForEntity("http://localhost:" + port + "/api/bookings/"+obj.getString("id"),
				Booking.class);
		int statusCode=requestControl.getStatusCodeValue();
		assertEquals(statusCode,204);
		
	}
	
	@Test
	public void deleteBookingNonCorrect() throws Exception {
		ResponseEntity<Boolean> resp=restTemplate.exchange("http://localhost:" + port + "/api/bookings/61a8c480648e0931525c22a1", HttpMethod.DELETE, null, Boolean.class);
		assertEquals(resp.getBody(),false);
	}
	
	@Test
	public void getByIdBookingCorrect() throws Exception {
		ResponseEntity<Booking> requestControl=this.restTemplate.getForEntity("http://localhost:" + port + "/api/bookings/61a8c480648e0931525c2697",
				Booking.class);
		int statusCode=requestControl.getStatusCodeValue();
		assertEquals(statusCode,200);
		assertEquals(requestControl.getBody().getId(),"61a8c480648e0931525c2697");
		
	}
	
	@Test
	public void checkBookingCorrect() throws Exception {
		CheckBookingRequest request=new CheckBookingRequest();
		request.bookingNr="61a8c480648e0931525c2697";
		request.eventId="61a88e8efacdaf0af830bd2c";
		ResponseEntity<String> bookingCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/bookings/check", request, String.class);
		assertEquals(bookingCreated.getStatusCode(), HttpStatus.OK);
		assertEquals(bookingCreated.getBody(), "ACCESS GRANTED");
		
		
	}
	
	@Test
	public void checkBookingRefused() throws Exception {
		CheckBookingRequest request=new CheckBookingRequest();
		request.bookingNr="61a8c480648e0931525c2697";
		request.eventId="61a88e8efacdaf0af830bd2b";
		ResponseEntity<String> bookingCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/bookings/check", request, String.class);
		assertEquals(bookingCreated.getStatusCode(), HttpStatus.OK);
		assertEquals(bookingCreated.getBody(), "ACCESS REFUSED");
		
		
	}
	
	@Test
	public void checkBookingInvalid() throws Exception {
		CheckBookingRequest request=new CheckBookingRequest();
		request.bookingNr="61a8c480648e0931525c22a1";
		request.eventId="61a88e8efacdaf0af830bd2b";
		ResponseEntity<String> bookingCreated = restTemplate.postForEntity("http://localhost:" + port + "/api/bookings/check", request, String.class);
		assertEquals(bookingCreated.getStatusCode(), HttpStatus.OK);
		assertEquals(bookingCreated.getBody(), "INVALID BOOKING CODE");
		
		
	}

}
