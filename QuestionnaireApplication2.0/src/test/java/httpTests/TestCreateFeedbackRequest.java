package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.CreateChoiceRequest;
import httpRequestsAndResponses.CreateFeedbackRequest;

public class TestCreateFeedbackRequest {

	@Test
	public void test() {
		CreateFeedbackRequest cfr = new CreateFeedbackRequest();
		cfr.setDescription("description");
		assertEquals("description", cfr.getDescription());
		
		cfr.setMemberID("TestName");
		assertEquals("TestName", cfr.getMemberID());
		
		cfr.setAltid("Altid");
		assertEquals("Altid", cfr.getAltid());
		
		cfr.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", cfr.getChoiceID());
		
		
		cfr = new CreateFeedbackRequest("TestName", "Altid", "description","ChoiceID");
		assertEquals("description", cfr.getDescription());
		assertEquals("TestName", cfr.getMemberID());
		assertEquals("Altid", cfr.getAltid());
		assertEquals("ChoiceID", cfr.getChoiceID());
	}

}
