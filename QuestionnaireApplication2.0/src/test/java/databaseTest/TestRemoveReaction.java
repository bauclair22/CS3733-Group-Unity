package databaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DAO;
//tested and works
public class TestRemoveReaction {
	@Test
	public void test() {
		DAO dao = new DAO();
		String alt1 ="c27f4e28-3a18-4a19-b3ec-cdf84edcf976";
		String alt2 = "bf810642-a0e2-45e0-a92a-820221c8cee6";
		String alt3 = "d43fb93f-462a-42f9-947d-29757e4a83ef";
		String mem1 ="6805ad59-0bb4-4c24-aa88-21f6cf758ba0";
		String mem2 ="e193ccb9-d584-4f9a-ae15-6498db9b6d31";
		String mem3 ="1c6d4fa1-de01-4c0d-8cc1-03bdcf15d455";
		
		try {;
			dao.deleteReaction(mem1,alt1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
