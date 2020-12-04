package databaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DAO;
import model.Alternative;
import model.Choice;

public class TestGetChoiceAlternatives {

	@Test
	public void test() {
		DAO dao = new DAO();
		try {;
			Alternative[] a = dao.getChoiceAlternatives("9f5f69de-791b-41fe-85ea-75425cdeb9bc");
			
				System.out.printf("Titles: " + a[0].getTitle() +
						" ," + a[1].getTitle() + " ," + a[2].getTitle());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
