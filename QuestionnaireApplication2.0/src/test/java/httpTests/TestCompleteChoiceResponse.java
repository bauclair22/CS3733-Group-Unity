package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.CompleteChoiceResponse;
import model.Alternative;
import model.Choice;

public class TestCompleteChoiceResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		CompleteChoiceResponse ccr = new CompleteChoiceResponse (c, 200);
		String rep = ccr.toString();
		assertTrue(rep.startsWith("CompleteChoiceResponse"));
		
		ccr = new CompleteChoiceResponse (403, "some error");
		rep = ccr.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
