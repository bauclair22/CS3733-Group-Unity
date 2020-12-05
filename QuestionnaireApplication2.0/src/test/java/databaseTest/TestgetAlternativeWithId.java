package databaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DAO;
import model.Alternative;
import model.Choice;

public class TestgetAlternativeWithId {

	@Test
	public void test() {
		DAO dao = new DAO();
		try {;
			Alternative a = dao.getAlternativewithID("bf810642-a0e2-45e0-a92a-820221c8cee6");
			System.out.println(a.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
