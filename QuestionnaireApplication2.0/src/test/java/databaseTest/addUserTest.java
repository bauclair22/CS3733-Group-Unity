package databaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DAO;

public class addUserTest {

	@Test
	public void test() {
		DAO dao = new DAO();
		String[] titles = {"first3", "second3", "third3"};
		String[] descriptions = {"is first3", "is second3", "is third3"};
		try {
			String Cid = dao.addApprover("Hoang" ,"00176406-e8a4-47bb-bacf-f4ab4cffb3c2");
			
			//dao.addUser("not add", "too many", Cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
