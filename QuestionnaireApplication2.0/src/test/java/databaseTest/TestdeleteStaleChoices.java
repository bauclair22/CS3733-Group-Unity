package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import database.DAO;

public class TestdeleteStaleChoices {

	@Test
	public void test() {
		DAO dao = new DAO();
		LocalDateTime myTime = LocalDateTime.now();
		Timestamp today= Timestamp.valueOf(myTime);
		int numdays = 6;
		Timestamp expiration = new Timestamp(today.getYear(), today.getMonth(), (today.getDate()- numdays), today.getHours(),
				today.getMinutes(), today.getSeconds(), today.getNanos());
		System.out.println("today:" + today.toString()+ "  Expiration date:" + expiration.toString());
		//try {
		//	ans1 = dao.isCompleted(choiceid1);
		//	ans2 = dao.isCompleted(choiceid2);
		//	ans3 = dao.isCompleted(choiceid3);
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		//assertEquals(ans1, true);
		//assertEquals(ans2, false);
		//assertEquals(ans3, false);
	}

}
