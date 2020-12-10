package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Feedback;
import java.sql.Timestamp;

public class testFeedback {

	//the calendar and time stamp have not been test ed
	
		@Test
		public void feedbackConstructor() {
			
			Feedback f = new Feedback("MemberName", "AltID", "description", "timeStamp2");
			assertTrue(f.getDescription() == "description");
			assertTrue(f.getMemberName() == "MemberName");
			assertEquals(f.getTimestamp(), "timeStamp2");
		}
		
		@Test
		public void feedbackToString() {
			Feedback f = new Feedback("MemberName", "AltID", "description", "timeStamp2");
			String rep = f.toString();
			assertTrue(rep.startsWith("["));
		}

}
