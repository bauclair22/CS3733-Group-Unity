package httpTests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import httpRequestsAndResponses.RefreshChoiceResponse;
import model.Alternative;
import model.Choice;

public class TestRefreshChoiceResponse {

	@Test
	public void test() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		RefreshChoiceResponse rcr = new RefreshChoiceResponse (c, 200);
		String rep = rcr.toString();
		assertTrue(rep.startsWith("["));
		
		rcr = new RefreshChoiceResponse (422, "Error");
		rep = rcr.toString();
		assertTrue(rep.startsWith("Error"));
	}

}
