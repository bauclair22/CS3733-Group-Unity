package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.RefreshChoiceRequest;

public class TestRefreshChoiceRequest {

	@Test
	public void test() {
		RefreshChoiceRequest rcr = new RefreshChoiceRequest();
		rcr.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", rcr.getChoiceID());
		
		rcr.setMemberID("MemberID");
		assertEquals("MemberID", rcr.getMemberID());
		
		rcr = new RefreshChoiceRequest("ChoiceID", "MemberID");
		assertEquals("ChoiceID", rcr.getChoiceID());
		assertEquals("MemberID", rcr.getMemberID());
	}

}
