package databaseTest;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.DAO;
import model.Choice;
import model.ChoiceReport;

public class TestGetAllChoices {

	@Test
	public void test() {
		DAO dao = new DAO();
		List<ChoiceReport> choices;
		try {;
			choices = dao.getAllChoices();
			for(int i=0; i<choices.size(); i++) {
				ChoiceReport c = choices.get(i);
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
