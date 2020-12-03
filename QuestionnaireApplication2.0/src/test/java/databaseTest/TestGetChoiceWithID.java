package databaseTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DAO;
import model.Alternative;
import model.Choice;

public class TestGetChoiceWithID {
	
	@Test
	public void test() {
		DAO dao = new DAO();
		try {;
			Choice c = dao.getChoiceswithID("9f5f69de-791b-41fe-85ea-75425cdeb9bc");
			
				System.out.printf("choice ID: " + c.getID() +
						" description: " + c.getdescription() 
						+ " maxUsers: " + c.getnumMembers() 
						+ " DateCreated: " + c.getDateCompleted() 
						+ " isCompleated: "+ c.getIsCompleted());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
