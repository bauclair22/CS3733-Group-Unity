package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Feedback;

public class testFeedback {

	//the calendar and time stamp have not been test ed
	
		@Test
		public void feedbackConstructor() {
			
			Feedback f = new Feedback("Bob", "hi");
			assertTrue(f.getDescription() == "hi");
			assertTrue(f.getMemberName() == "Bob");
		}

}
