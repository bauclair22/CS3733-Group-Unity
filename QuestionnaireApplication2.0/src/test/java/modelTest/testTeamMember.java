package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.TeamMember;

public class testTeamMember {

	TeamMember bob;
	TeamMember billy;
	
	@Before
	public void setup() {
		bob = new TeamMember("Bob", "p");
		billy = new TeamMember("Billy", null);
	}
	
	@Test
	public void teamMemberConstructor_withPassword() {
		assertTrue(bob.getName() == "Bob");
		assertTrue(bob.getPassword() == "p");
	}
	
	@Test
	public void teamMemberConstructor_withoutPassword() {
		assertTrue(billy.getName() == "Billy");
		assertTrue(billy.getPassword() == null);
	}
	
	@Test
	public void isEqual_usingNameAndPassword() {
		assertTrue(bob.isEqual("Bob","p"));
		assertFalse(bob.isEqual("bob","p"));
		assertFalse(bob.isEqual("Bob","d"));
		assertFalse(bob.isEqual("Bob",null));
		assertFalse(bob.isEqual(null,"p"));
	}
	
	@Test
	public void isEqual_usingTeamMember() {
		TeamMember bob2 = new TeamMember("Bob", "p");
		assertTrue(bob.isEqual(bob2));
		assertFalse(bob.isEqual(billy));
	}
	
	//noticed createChoice for the teamMember. Might be something that we might wanna bring to a higher level.

}
