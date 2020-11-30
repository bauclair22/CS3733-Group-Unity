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
			String Cid = dao.createChoice(3,"Testing exeding max users", titles, descriptions);
			dao.addUser("maxUser1", "maxUser1Password31", Cid);
			dao.addUser("maxUser2", "maxUser2Password32", Cid);
			dao.addUser("maxUser3", "maxUser3Password33", Cid);
			dao.addUser("maxUsershouldNotAdd", "maxUserPassword34", Cid);
			//dao.addUser("not add", "too many", Cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
