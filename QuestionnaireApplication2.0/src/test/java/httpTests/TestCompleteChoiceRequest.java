package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.CompleteChoiceRequest;

public class TestCompleteChoiceRequest {

	@Test
	public void test() {
		CompleteChoiceRequest ccr = new CompleteChoiceRequest();
		ccr.setAltid("Altid");
		assertEquals("Altid", ccr.getAltid());
		
		ccr.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", ccr.getChoiceID());
		
		ccr = new CompleteChoiceRequest("ChoiceID", "Altid");
		assertEquals("Altid", ccr.getAltid());
		assertEquals("ChoiceID", ccr.getChoiceID());
	}

}
