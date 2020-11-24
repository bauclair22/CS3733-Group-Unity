package databaseTest;

import static org.junit.Assert.*;
import database.DAO;
import org.junit.Test;

public class createChoiceTest {

	@Test
	public void test1() {
		DAO dao = new DAO();
		try {
			String Cid = dao.createChoice(2,"hi");
			try {
				dao.createAlternative("title", "description", Cid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
