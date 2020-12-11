package httpTests;

import static org.junit.Assert.*;

import org.junit.Test;
import httpRequestsAndResponses.DeleteStaleRequest;

public class TestDeleteStaleRequest {

	@Test
	public void test() {
		DeleteStaleRequest dsr = new DeleteStaleRequest();
		dsr.setDaysOld((float) 6.5);
		float result = dsr.getDaysOld();
		assertTrue(result == 6.5);
		
		dsr = new DeleteStaleRequest((float) 6.5);
		result = dsr.getDaysOld();
		assertTrue(result == 6.5);
		String rep = dsr.toString();
		assertTrue(rep.startsWith("DeleteStaleRequest"));
	}

}
