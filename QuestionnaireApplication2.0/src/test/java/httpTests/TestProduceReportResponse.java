package httpTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import httpRequestsAndResponses.ProduceReportResponse;
import model.ChoiceReport;

public class TestProduceReportResponse {

	@Test
	public void test() {
		ChoiceReport cr = new ChoiceReport("choiceID1", "choiceDescription1", true,  "dateCompleted1");
		ChoiceReport cr2 = new ChoiceReport("choiceID2", "choiceDescription2", true,  "dateCompleted2");
		List<ChoiceReport> lcr = new ArrayList<ChoiceReport>();
		ProduceReportResponse prr = new ProduceReportResponse (403, "Error");
		String rep = prr.toString();
		assertTrue(rep.startsWith("Empty"));
		
		lcr.add(cr);
		lcr.add(cr2);
		prr = new ProduceReportResponse (lcr, 200);
		rep = prr.toString();
		assertTrue(rep.startsWith("AllChoices"));
	}

}
