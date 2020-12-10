package httpTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import httpRequestsAndResponses.DeleteStaleResponse;
import model.ChoiceReport;

public class TestDeleteStaleResponse {

	@Test
	public void test() {
		ChoiceReport cr = new ChoiceReport("choiceID1", "choiceDescription1", true,  "dateCompleted1");
		ChoiceReport cr2 = new ChoiceReport("choiceID2", "choiceDescription2", true,  "dateCompleted2");
		List<ChoiceReport> lcr = new ArrayList<ChoiceReport>();
		DeleteStaleResponse dsr = new DeleteStaleResponse (403, "Error");
		String rep = dsr.toString();
		assertTrue(rep.startsWith("Empty"));
		
		lcr.add(cr);
		lcr.add(cr2);
		dsr = new DeleteStaleResponse (lcr, 200);
		rep = dsr.toString();
		assertTrue(rep.startsWith("AllChoices"));
	}

}
