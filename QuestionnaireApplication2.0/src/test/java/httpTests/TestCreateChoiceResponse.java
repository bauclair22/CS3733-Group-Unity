package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.CreateChoiceResponse;


public class TestCreateChoiceResponse {

	@Test
	public void test() {
		CreateChoiceResponse ccr = new CreateChoiceResponse ("Choice1", 200);
		String rep = ccr.toString();
		assertTrue(rep.startsWith("Response"));
		
		ccr = new CreateChoiceResponse ("some error", 403);
		rep = ccr.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
