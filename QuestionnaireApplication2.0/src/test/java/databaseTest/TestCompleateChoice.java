package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import database.DAO;

public class TestCompleateChoice {
//tested and works
	@Test
	public void test() {
		DAO dao = new DAO();
		LocalDateTime myTime = LocalDateTime.now();
		Timestamp ts= Timestamp.valueOf(myTime);
		try {
			dao.completeChoice("9762f2ef-0bfa-48b0-9fff-160df7ab450a");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
