package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.UnselectReactionRequest;

public class TestUnselectReactionRequest {

	@Test
	public void test() {
		UnselectReactionRequest urr = new UnselectReactionRequest();
		urr.setAltid("AltID");
		assertEquals("AltID", urr.getAltid());
		
		urr.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", urr.getChoiceID());
		
		urr.setmemberID("MemberID");
		assertEquals("MemberID", urr.getmemberID());
		
		urr = new UnselectReactionRequest("MemberID", "AltID", "ChoiceID");
		assertEquals("AltID", urr.getAltid());
		assertEquals("ChoiceID", urr.getChoiceID());
		assertEquals("MemberID", urr.getmemberID());
	}

}
