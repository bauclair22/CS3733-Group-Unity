package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.SelectApprovalResponse;
import model.Alternative;
import model.Choice;

public class TestSelectApprovalResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		SelectApprovalResponse sar = new SelectApprovalResponse (c, 200);
		String rep = sar.toString();
		assertTrue(rep.startsWith("ApprovalResponse"));
		
		sar = new SelectApprovalResponse (422, "Error");
		rep = sar.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
