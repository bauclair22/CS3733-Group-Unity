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
		
		cfr.setMemberName("TestName");
		assertEquals("TestName", cfr.getMemberName());
		
		cfr = new CreateFeedbackRequest("TestName", "description");
		assertEquals("description", cfr.getDescription());
		assertEquals("TestName", cfr.getMemberName());
	}

}
