package modelTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import model.Alternative;
import model.Choice;

public class TestChoice {

	@Test
	public void testAddTeamMembers() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		c.addTeamMember("Member1");
		ArrayList<String> mems = new ArrayList<String>();
		mems.add("Member1");
		assertEquals(mems, c.getTeamMembers());
	}
	
	@Test
	public void testAddAlternative() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		Alternative AltC = new Alternative("titleC", "altIDC");
		c.addAlternative(AltC);
		alts[2] = AltC;
		assertEquals(alts, c.getAlternatives());
	}
	
	@Test
	public void testGetID() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		assertEquals("ID", c.getID());
	}
	
	@Test
	public void testIsCompleted() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		c.setIsCompleted(true);
		assertTrue(c.getIsCompleted());
	}
	
	@Test
	public void testDateCompleted() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		Timestamp newDate = new Timestamp(5);
		c.setDateCompleted(newDate);
		assertEquals(newDate, c.getDateCompleted());
	}
	
	@Test
	public void testGetDescription() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		assertEquals("description", c.getdescription());
	}
	
	@Test
	public void testGetNumMembers() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		assertEquals(4, c.getnumMembers());
	}
	
//	@Test
//	public void testPrintAlternatives() {
//		Alternative AltA = new Alternative("titleA", "altIDA");
//		Alternative AltB = new Alternative("titleB", "altIDB");
//		Alternative[] alts = new Alternative[5];
//		alts[0] = AltA;
//		alts[1] = AltB;
//		Timestamp date = new Timestamp(0);
//		Choice c = new Choice("ID", "description", alts, 4, date);
//		String rep = c.printAternativeTitles();
//		assertTrue(rep.startsWith("titleA"));
//	}

	@Test
	public void testChoiceReport() {
		Alternative AltA = new Alternative("titleA", "altIDA");
		Alternative AltB = new Alternative("titleB", "altIDB");
		Alternative[] alts = new Alternative[5];
		alts[0] = AltA;
		alts[1] = AltB;
		Timestamp date = new Timestamp(0);
		Choice c = new Choice("ID", "description", alts, 4, date);
		String rep = c.choicereport();
		assertTrue(rep.startsWith("["));
	}
}
