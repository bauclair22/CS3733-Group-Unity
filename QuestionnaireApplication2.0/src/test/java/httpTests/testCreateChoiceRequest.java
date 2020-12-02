package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.CreateChoiceRequest;

public class testCreateChoiceRequest {

	@Test
	public void test() {
		CreateChoiceRequest ccr = new CreateChoiceRequest();
		ccr.setDescription("description");
		assertEquals("description", ccr.getDescription());
		
		ccr.setNumMembers(3);
		assertEquals(3, ccr.getNumMembers());
		
		String testTitles[] = new String[20];
		testTitles[0] = "title1";
		testTitles[1] = "title2";
		ccr.setAlternativeTitles(testTitles);
		assertEquals(testTitles[0], ccr.getAlternativeTitle(0));
		assertEquals(testTitles[1], ccr.getAlternativeTitle(1));
		
		ccr = new CreateChoiceRequest("description", 3, testTitles);
		assertEquals("description", ccr.getDescription());
		assertEquals(3, ccr.getNumMembers());
		assertEquals(testTitles[0], ccr.getAlternativeTitle(0));
		assertEquals(testTitles[1], ccr.getAlternativeTitle(1));
	}

}
