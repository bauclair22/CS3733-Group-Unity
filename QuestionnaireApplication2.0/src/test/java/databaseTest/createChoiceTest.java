package databaseTest;

import static org.junit.Assert.*;
import database.DAO;
import org.junit.Test;

public class createChoiceTest {

	@Test
	public void test1() {
		DAO dao = new DAO();
		String[] titles = {"first", "second", "third"};
		try {
			String Cid = dao.createChoice(2,"withAlternative", titles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddUser() {
		DAO dao = new DAO();
		String[] titles = {"first", "second", "third"};
		try {
			String Cid = dao.createChoice(3,"withAlternative", titles);
			dao.addUser("Carmen", "Password", Cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
