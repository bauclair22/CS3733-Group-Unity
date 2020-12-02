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
			dao.completeChoice("00e8cbb7-9173-4eb6-92b2-6bee02e2f0e1");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
