package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import database.DAO;

public class TestaddApprover {

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
	
	@Test
	public void test1() {
		DAO dao = new DAO();
		try {
			dao.addApprover("03ab1518-721a-4480-a2b2-04b2381962c2");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
