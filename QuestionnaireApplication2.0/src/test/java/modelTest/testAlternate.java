package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Alternative;
import model.Feedback;
import model.TeamMember;

import java.sql.Timestamp;

public class testAlternate {

	Alternative alt;
	TeamMember David;
	TeamMember Bob;
	TeamMember Ben;
	Feedback fromDavid;
	
	@Before
	public void setUp() {
		
		Ben = new TeamMember("Ben","Ten");
		David = new TeamMember("David", "password");
		Bob = new TeamMember("Bob");
		fromDavid = new Feedback("David", "id76",  "I think your idea is bad", "");
		
		alt = new Alternative("alt1" ,"id76");
		alt.addApprover("Bob");
		alt.addApprover("David");
		alt.addDisapprover("Ben");
		alt.addFeedback(fromDavid);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(alt.getTitle() == "alt1");
		
		
		//make sure that the (dis)approvers are the correct size
		assertTrue(alt.getApprovers().size() == 2);
		assertTrue(alt.getDisapprovers().size() == 1);
		
		//make sure that the teamMembers are in the right list
		assertTrue(alt.getApprovers().contains("Bob"));
		assertTrue(alt.getApprovers().contains("David"));
		assertFalse(alt.getApprovers().contains("Ben"));
		
		assertFalse(alt.getDisapprovers().contains("Bob"));
		assertFalse(alt.getDisapprovers().contains("David"));
		assertTrue(alt.getDisapprovers().contains("Ben"));
	}

}
