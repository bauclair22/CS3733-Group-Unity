package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;

import httpRequestsAndResponses.SelectApprovalRequest;

public class TestSelectApprovalRequest {

	@Test
	public void test() {
		SelectApprovalRequest sar = new SelectApprovalRequest();
		sar.setAltid("AltID");
		assertEquals("AltID", sar.getAltid());
		
		sar.setChoiceID("ChoiceID");
		assertEquals("ChoiceID", sar.getChoiceID());
		
		sar.setmemberID("MemberID");
		assertEquals("MemberID", sar.getmemberID());
		
		sar = new SelectApprovalRequest("MemberID", "AltID", "ChoiceID");
		assertEquals("AltID", sar.getAltid());
		assertEquals("ChoiceID", sar.getChoiceID());
		assertEquals("MemberID", sar.getmemberID());
		String rep = sar.toString();
		assertTrue(rep.startsWith("MemberID"));
	}

}
