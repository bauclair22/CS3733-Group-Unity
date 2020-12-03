package databaseTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import database.DAO;
import model.Choice;
import model.Feedback;

public class TestGetChoiceReaction {

	@Test
	public void test() {
		DAO dao = new DAO();
		ArrayList<Feedback> fb;
		String alt1 ="c27f4e28-3a18-4a19-b3ec-cdf84edcf976";
		try {
			fb = dao.getAlternativesFeedback(alt1);
			for(int i=0; i<fb.size(); i++) {
				Feedback f = fb.get(i);
				System.out.println("memberName: " + f.getMemberName() +
						" description: " + f.getDescription() 
						+ " time stamp: " + f.getTimestamp() + "\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
