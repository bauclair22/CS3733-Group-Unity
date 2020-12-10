package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.ParticipateChoiceRequest;

public class TestParticipateChoiceRequest {

	@Test
	public void test() {
		ParticipateChoiceRequest pcr = new ParticipateChoiceRequest();
		pcr.setId("ChoiceID");
		assertEquals("ChoiceID", pcr.getId());
		
		pcr.setPassword("Password");
		assertEquals("Password", pcr.getPassword());
		
		pcr.setUsername("MyUsername");
		assertEquals("MyUsername", pcr.getUsername());
		
		pcr = new ParticipateChoiceRequest("MyUsername", "Password", "ChoiceID");
		assertEquals("ChoiceID", pcr.getId());
		assertEquals("Password", pcr.getPassword());
		assertEquals("MyUsername", pcr.getUsername());
	}

}
