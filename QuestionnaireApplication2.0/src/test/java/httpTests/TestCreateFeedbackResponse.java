package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.CreateFeedbackResponse;
import model.Alternative;
import model.Choice;

public class TestCreateFeedbackResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		CreateFeedbackResponse cfr = new CreateFeedbackResponse (c);
		String rep = cfr.toString();
		assertTrue(rep.startsWith("Response"));
		
		cfr = new CreateFeedbackResponse ("some error", 403);
		rep = cfr.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
