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
		try {
			Alternative a = dao.getAlternativewithID("006729fb-830d-4ba5-b7f0-e829234d9673");
			System.out.println(a.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
