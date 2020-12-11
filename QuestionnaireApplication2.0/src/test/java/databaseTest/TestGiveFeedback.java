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
		String memberID ="13eba7d4-905f-47c3-9505-ba841ba7fdb5"; //iceiking981
		String altid="006729fb-830d-4ba5-b7f0-e829234d9673";
		String feedback="I am very happy2";
		Feedback fb = null;
		try {
			fb = dao.giveFeedback("f8c9c86d-c463-4e4f-b729-b4e92bbc1520","226c3854-9d3f-472b-b3bf-0bbcba876933","testFeedback", "438e6414-6826-431a-a8e1-67b33210da28");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(fb.toString());
	}

}
