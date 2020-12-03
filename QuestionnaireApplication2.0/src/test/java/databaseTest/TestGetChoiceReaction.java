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
		String alt2 ="bf810642-a0e2-45e0-a92a-820221c8cee6";
		try {
			fb = dao.getAlternativesFeedback(alt2);
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
