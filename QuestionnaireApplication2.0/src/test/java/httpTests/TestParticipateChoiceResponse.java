package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.ParticipateChoiceResponse;
import model.Alternative;
import model.Choice;

public class TestParticipateChoiceResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		ParticipateChoiceResponse pcr = new ParticipateChoiceResponse (c, "memberID");
		String rep = pcr.toString();
		assertTrue(rep.startsWith("Your choice"));
		
		pcr = new ParticipateChoiceResponse ("Error", 422);
		rep = pcr.toString();
		assertTrue(rep.startsWith("ErrorResponse"));
	}

}
