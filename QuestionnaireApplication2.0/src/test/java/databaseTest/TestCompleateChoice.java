package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import database.DAO;

public class TestCompleateChoice {

	@Test
	public void test() {
		DAO dao = new DAO();
		LocalDateTime myTime = LocalDateTime.now();
		Timestamp ts= Timestamp.valueOf(myTime);
		try {
			dao.completeChoice(ts ,"03ab1518-721a-4480-a2b2-04b2381962c2");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
