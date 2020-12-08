package databaseTest;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import database.DAO;
import model.Feedback;

public class TestIsCompleted {

	@Test
	public void test() {
		DAO dao = new DAO();
		String choiceid1 ="03ab1518-721a-4480-a2b2-04b2381962c2"; //completed
		String choiceid2="24f209d8-343f-4a54-a90d-e9aed5ed3647"; //not completed
		String choiceid3="39f4c5a0-c03a-4403-bfd1-ec60774a15b2"; //not completed
		boolean ans1 = false;
		boolean ans2 = false;
		boolean ans3 = false;
		try {
			ans1 = dao.isCompleted(choiceid1);
			ans2 = dao.isCompleted(choiceid2);
			ans3 = dao.isCompleted(choiceid3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(ans1, true);
		assertEquals(ans2, false);
		assertEquals(ans3, false);
	}
}
