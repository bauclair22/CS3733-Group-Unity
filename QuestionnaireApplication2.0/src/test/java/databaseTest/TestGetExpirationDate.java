package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import database.DAO;

public class TestGetExpirationDate {

	@Test
	public void test() {
		DAO dao = new DAO();
		float days = 2.5f;
		Timestamp expiration = dao.getExpirationDate(days);
		System.out.println("Expiration date:" + expiration.toString());
	}

}
