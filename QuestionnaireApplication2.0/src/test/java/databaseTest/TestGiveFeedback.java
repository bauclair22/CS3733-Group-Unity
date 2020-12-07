package databaseTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DAO;
import model.Feedback;

public class TestGiveFeedback {

	@Test
	public void test() {
		DAO dao = new DAO();
		String memberID ="03ea1a71-fa8c-4b41-9d62-f4fd54e5908d"; //iceiking981
		String altid="b654273c-a5b6-4598-91db-bcd2fcd4054f";
		String feedback="I am very happy";
		Feedback fb = null;
		try {
			fb = dao.giveFeedback(memberID,altid, feedback);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(fb.toString());
	}

}
