package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.UnselectReactionResponse;
import model.Alternative;
import model.Choice;

public class TestUnselectReactionResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		UnselectReactionResponse urr = new UnselectReactionResponse (c, 200);
		String rep = urr.toString();
		assertTrue(rep.startsWith("UnselectResponse"));
		
		urr = new UnselectReactionResponse (422, "Error");
		rep = urr.toString();
		assertTrue(rep.startsWith("ErrorResult"));
	}

}
