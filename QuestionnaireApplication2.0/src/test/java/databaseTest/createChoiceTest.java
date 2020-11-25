package databaseTest;

import static org.junit.Assert.*;
import database.DAO;
import org.junit.Test;

public class createChoiceTest {

	@Test
	public void test1() {
		DAO dao = new DAO();
		String[] titles = {"first", "second", "third"};
		String[] descriptions = {"is first", "is second", "is third"};
		try {
			String Cid = dao.createChoice(2,"withAlternative", titles, descriptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
