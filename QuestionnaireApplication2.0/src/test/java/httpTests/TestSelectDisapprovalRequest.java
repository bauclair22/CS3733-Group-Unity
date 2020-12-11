package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.SelectDisapprovalRequest;

public class TestSelectDisapprovalRequest {

	@Test
	public void test() {
		SelectDisapprovalRequest sdr = new SelectDisapprovalRequest();
		sdr.setAltid("AltID");
		assertEquals("AltID", sdr.getAltid());
		
		sdr.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", sdr.getChoiceID());
		
		sdr.setmemberID("MemberID");
		assertEquals("MemberID", sdr.getmemberID());
		
		sdr = new SelectDisapprovalRequest("MemberID", "AltID", "ChoiceID");
		assertEquals("AltID", sdr.getAltid());
		assertEquals("ChoiceID", sdr.getChoiceID());
		assertEquals("MemberID", sdr.getmemberID());
		String rep = sdr.toString();
		assertTrue(rep.startsWith("MemberID"));
	}

}
