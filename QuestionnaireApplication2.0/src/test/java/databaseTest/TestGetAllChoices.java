package databaseTest;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DAO;
import model.Choice;

public class TestGetAllChoices {

	@Test
	public void test() {
		DAO dao = new DAO();
		List<Choice> choices;
		try {;
			choices = dao.getAllChoices();
			for(int i=0; i<choices.size(); i++) {
				Choice c = choices.get(i);
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
