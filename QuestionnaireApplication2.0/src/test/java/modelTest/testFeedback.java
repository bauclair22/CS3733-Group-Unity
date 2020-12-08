package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Feedback;
import java.sql.Timestamp;

public class testFeedback {

	//the calendar and time stamp have not been test ed
	
		@Test
		public void feedbackConstructor() {
			
			Feedback f = new Feedback("Bob", "ID2132","your idea is still bad",(Timestamp) null);
			assertTrue(f.getDescription() == "your idea is still bad");
			assertTrue(f.getMemberName() == "Bob");
		}

}
