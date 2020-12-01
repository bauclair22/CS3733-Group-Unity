package databaseTest;



import org.junit.Test;

import database.DAO;

public class TestAdduserWithoutpassword {

	@Test
	public void test() {
		DAO dao = new DAO();
		String[] titles = {"first3", "second3", "third3"};
		try {
			String Cid = dao.createChoice(3,"TestingNoPassword", titles);
			dao.addUser("UserwithoutPass", null, Cid);
			dao.addUser("UserwithoutPass", null, Cid);
			//dao.addUser("maxUser3", "maxUser3Password33", Cid);
			//dao.addUser("maxUsershouldNotAdd", "maxUserPassword34", Cid);
			//dao.addUser("not add", "too many", Cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
