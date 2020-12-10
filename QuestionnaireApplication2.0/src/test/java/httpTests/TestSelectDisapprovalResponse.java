package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.SelectDisapprovalResponse;
import model.Alternative;
import model.Choice;

public class TestSelectDisapprovalResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		SelectDisapprovalResponse sdr = new SelectDisapprovalResponse (c, 200);
		String rep = sdr.toString();
		assertTrue(rep.startsWith("DisapprovalResponse"));
		
		sdr = new SelectDisapprovalResponse (422, "Error");
		rep = sdr.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
