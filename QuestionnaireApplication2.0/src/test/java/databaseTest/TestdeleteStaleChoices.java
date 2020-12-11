package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import database.DAO;
import model.ChoiceReport;

public class TestdeleteStaleChoices {

	@Test
	public void test() {
		DAO dao = new DAO();
		float days = 7f;
		List<ChoiceReport> r = null;
		//run this in sql first to have something to delete:
		//INSERT INTO Choices (idChoice, maxUsers, description, DateCreated, isCompleted) VALUES ("choiceIDToDelete2", 3, "toTestDeleteOld2", "2020-11-08 00:38:28", 1);
		try {
			r =dao.deleteStaleChoices(days);
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Expiration date:" + r.toString());
	}

}
