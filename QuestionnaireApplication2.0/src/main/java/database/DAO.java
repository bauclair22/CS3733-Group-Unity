package database;
//import java.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import model.Choice;
import model.ChoiceReport;
import model.Alternative;
import model.Feedback;
import model.TeamMember;

public class DAO {
	java.sql.Connection conn;

	final String tblchoices = "Choices"; // Exact capitalization
	final String tblAlternative = "Alternatives";
	final String tblTeamMember = "TeamMember";
	final String viewFeedbacksWithName = "feedback_withName";
	final String tblFeedback = "Feedbacks";
	final String viewLikedBy = "Likedby";
	final String viewDislikedBy = "Dislikedby";
	final String tblReactions = "Reactions";
	final String viewCheckMembers = "checkMembers";

	public DAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	// input Choice id
	// returns choice object complete will all the alternatives and the alternatives
	// have reactions and feedback
	public Choice getChoiceswithID(String ID) throws Exception {

		try {
			Choice choice = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblchoices + " WHERE idChoice=?;"); //
			ps.setString(1, ID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int maxUsers = resultSet.getInt("maxUsers");
				String description = resultSet.getString("description");
				Timestamp time = resultSet.getTimestamp("DateCreated");
				boolean isCompletednum = resultSet.getBoolean("isCompleted");
				// boolean isCompleted = false;
				// if(isCompletednum == 1) {isCompleted=true;}
				Alternative[] alternatives = getChoiceAlternatives(ID);
				choice = new Choice(ID, description, alternatives, maxUsers, time);
				choice.setIsCompleted(isCompletednum);
			}
			resultSet.close();
			ps.close();

			return choice;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting choice: " + e.getMessage());
		}
	}

	// input choiceId
	// returns all the alternatives of the choice inputed, alternatives have
	// reactions and feedback
	public Alternative[] getChoiceAlternatives(String choiceid) throws Exception {

		try {
			Alternative A[] = new Alternative[5];
			int counter = 0;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblAlternative + " WHERE choiceID=?;");
			ps.setString(1, choiceid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String title = resultSet.getString("alternative");
				String altID = resultSet.getString("idAlternative");
				Alternative alt = new Alternative(title, altID);
				ArrayList<String> approvers = getLikedBy(altID);
				ArrayList<String> disapprovers = getDislikedBy(altID);
				ArrayList<Feedback> feedback = getAlternativesFeedback(altID);
				alt.setApprovers(approvers);
				alt.setDisapprovers(disapprovers);
				alt.setFeedback(feedback);
				A[counter] = alt;
				counter++;
			}
			resultSet.close();
			ps.close();

			return A;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting alternatives: " + e.getMessage());
		}
	}

	// input alternative ID
	// returns a list of srtings with the name of the usere's who liked the
	// alternative
	public ArrayList<String> getLikedBy(String altid) throws Exception {

		try {
			ArrayList<String> approvers = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewLikedBy + " WHERE alternativeID=?;");
			ps.setString(1, altid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				// String pass = resultSet.getString("password");
				// TeamMember member=new TeamMember(name,pass);
				approvers.add(name);
			}
			resultSet.close();
			ps.close();

			return approvers;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting approvers: " + e.getMessage());
		}
	}

	// input alternative ID
	// returns a list of srtings with the name of the usere's who disliked the
	// alternative
	public ArrayList<String> getDislikedBy(String altid) throws Exception {

		try {
			ArrayList<String> disapprovers = new ArrayList<>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT name FROM " + viewDislikedBy + " WHERE alternativeID=?;");
			ps.setString(1, altid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				String name = resultSet.getString("name");
				// String pass = resultSet.getString("password");
				// TeamMember member=new TeamMember(name,pass);
				disapprovers.add(name);
			}
			resultSet.close();
			ps.close();

			return disapprovers;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting disapprovers: " + e.getMessage());
		}
	}

	// input alternative id
	// returns a list with the feedback objects of the alternative
	public ArrayList<Feedback> getAlternativesFeedback(String altid) throws Exception {

		try {
			ArrayList<Feedback> feedback = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT time, feedback, name FROM " + viewFeedbacksWithName + " WHERE alternativeID=?;");
			ps.setString(1, altid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Timestamp time = resultSet.getTimestamp("time");
				String comment = resultSet.getString("feedback");
				String name = resultSet.getString("name");
				Feedback f = new Feedback(name, altid, comment, time.toString());
				feedback.add(f);
			}
			resultSet.close();
			ps.close();

			return feedback;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting feedback: " + e.getMessage());
		}
	}

	// stores a choice in the database and returns the choice ID randomly assigned
	// to it
	public String createChoice(int maxUsers, String description, String[] titleAlt) throws Exception {
		String newID = null;
		boolean flag = true;
		try {
			System.out.printf("trying to create choice" + "maxUsers: " + maxUsers + "description" + description);
			String query = "INSERT INTO " + tblchoices
					+ " (idChoice, maxUsers, description, DateCreated, isCompleted) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(query);
			newID = UUID.randomUUID().toString();
			ps.setString(1, newID);
			ps.setInt(2, maxUsers);
			ps.setString(3, description);
			LocalDateTime myTime = LocalDateTime.now();
			Timestamp ts = Timestamp.valueOf(myTime);
			ps.setTimestamp(4, ts);
			ps.setInt(5, 0);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			flag = false;
			newID = null;
			System.out.println(" failed to create choice ");
			throw new Exception("Failed to update report: " + e.getMessage());
		}

		if (flag) {
			for (int i = 0; i < titleAlt.length; i++) {
				createAlternative(titleAlt[i], newID);
			}
		}

		return newID;
	}

	// stores the alternative in the database and returns the alternative id
	// randomly assigned to it
	public String createAlternative(String title, String choiceID) throws Exception {
		String newID;
		try {
			String query = "INSERT INTO " + tblAlternative
					+ " (idAlternative, choiceID, alternative) VALUES (?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(query);
			newID = UUID.randomUUID().toString();
			ps.setString(1, newID);
			ps.setString(2, choiceID);
			ps.setString(3, title);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			newID = null;
			throw new Exception("Failed to update report: " + e.getMessage());
		}
		return newID;
	}

	// stores a new user, if that user already exists and the password is correct
	// nothing happens, if if the password is incorrect throws an error
	public boolean addUser(String Username, String password, String choiceid) throws Exception {

		boolean flagMatchFound = false;
		boolean added = false;
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblTeamMember + " WHERE name=? AND choiceID=?;");
			ps.setString(1, Username);
			ps.setString(2, choiceid);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				flagMatchFound = true;
				String correctPassword = resultSet.getString("password");
				if (correctPassword.contentEquals(password)) {
					added = true;
				} else {
					throw new Exception("Password is incorrect");
				}
			}
			resultSet.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in adding user: " + e.getMessage());
		}
		if (!flagMatchFound && canAdd(choiceid) && !isCompleted(choiceid)) {
			try {
				String query = "INSERT INTO " + tblTeamMember + "  VALUES (?, ?, ?, ?);";
				String newMemberID = UUID.randomUUID().toString();
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, newMemberID);
				ps.setString(2, choiceid);
				ps.setString(3, Username);
				ps.setString(4, password);
				ps.executeUpdate();
				ps.close();
				added = true;

			} catch (Exception e) {
				throw new Exception("Failed to update report: " + e.getMessage());
			}
		}
		return added;
	}

	// checks if there is space for a user to be added to choice without exceeding
	// the user limit
	public boolean canAdd(String ChoiceID) throws Exception {
		boolean canadd = false;
		int participating = 0;
		int max = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT count(idTeamMember) as participating, maxUsers FROM "
					+ tblTeamMember + " join " + tblchoices + " WHERE choiceID = idChoice AND choiceID=?;");
			ps.setString(1, ChoiceID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				participating = resultSet.getInt("participating");
				max = resultSet.getInt("maxUsers");
				System.out.printf("max: " + max + " participating: " + participating);

			}
			if (max > participating || max == 0) {
				canadd = true;
			} else {
				throw new Exception("Choice is full: No more users allowed");
			}
			resultSet.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in checking users: " + e.getMessage());
		}
		return canadd;
	}

	// deletes a reaction and returns a boolean of weather the delete was successful
	public boolean deleteReaction(String memberID, String altid, String choiceID) throws Exception {
		if (isCompleted(choiceID)) {
			throw new Exception("Choice is complete you can't react");
		}
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM " + tblReactions + " WHERE memberID=? AND alternativeID=?;");
			ps.setString(1, memberID);
			ps.setString(2, altid);
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to delete reaction: " + e.getMessage());
		}
	}

	// returns a list of choiceReports including all the choices in existence
	public List<ChoiceReport> getAllChoices() throws Exception {

		List<ChoiceReport> allChoices = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblchoices + ";";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				ChoiceReport c = generateChoice(resultSet);
				allChoices.add(c);
			}
			resultSet.close();
			statement.close();
			return allChoices;

		} catch (Exception e) {
			throw new Exception("Failed in getting choices: " + e.getMessage());
		}
	}

	// used by getAllChoices to create the choiceReport object
	private ChoiceReport generateChoice(ResultSet resultSet) throws Exception {
		// String description = resultSet.getString("description");
		// Need something here to create the alternatives in the choice or need to
		// change the choice constructor
		// Alternative[] alternatives = new Alternative[5];
		// int numMembers = resultSet.getInt("maxUsers");
		boolean isCompleted = resultSet.getBoolean("isCompleted");
		String ID = resultSet.getString("idChoice");
		Timestamp date = resultSet.getTimestamp("DateCreated");
		String desc = resultSet.getString("description");
		String s = null;
		if (date != null) {
			s = date.toString();
		}
		return new ChoiceReport(ID, desc, isCompleted, s);
	}

	// adds a new record of an approver to the database and returns a boolean of
	// weather it succeed
	public boolean addApprover(String memberID, String altid, String choiceID) throws Exception {
		boolean success = false;
		if (isCompleted(choiceID)) {
			throw new Exception("Choice is complete you can't react");
		}
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblReactions + " WHERE memberID = ? AND alternativeID = ?;");
			ps.setString(1, memberID);
			ps.setString(2, altid);
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				resultSet.close();
				throw new Exception("User has already approved or disapproved this alternative");
			}
			ps = conn.prepareStatement("INSERT INTO " + tblReactions
					+ " (idReaction, alternativeID, memberID, reaction) values(?,?,?,?);");
			String newID = UUID.randomUUID().toString();
			ps.setString(1, newID);
			ps.setString(2, altid);
			ps.setString(3, memberID);
			ps.setString(4, "Like");
			ps.execute();
			success = true;

		} catch (Exception e) {
			success = false;
			throw new Exception("Failed to insert approver: " + e.getMessage());
		}
		return success;
	}

	// adds a new record of a disapprover to the database and returns a boolean of
	// weather it succeed
	public boolean addDisapprover(String memberID, String altid, String choiceID) throws Exception {
		boolean success = false;
		if (isCompleted(choiceID)) {
			throw new Exception("Choice is complete you can't react");
		}
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblReactions + " WHERE memberID =? AND alternativeID =?;");
			ps.setString(1, memberID);
			ps.setString(2, altid);
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				resultSet.close();
				throw new Exception("User has already approved or disapproved this alternative");
			}
			ps = conn.prepareStatement("INSERT INTO " + tblReactions
					+ " (idReaction, alternativeID, memberID, reaction) values(?,?,?,?);");
			String newID = UUID.randomUUID().toString();
			ps.setString(1, newID);
			ps.setString(2, altid);
			ps.setString(3, memberID);
			ps.setString(4, "Dislike");
			ps.execute();
			success = true;

		} catch (Exception e) {
			success = false;
			throw new Exception("Failed to insert disapprover: " + e.getMessage());
		}
		return success;
	}

	// Deletes all choices n days old an returns an updated list of choiceReports
	public List<ChoiceReport> deleteStaleChoices(float n) throws Exception {
		Timestamp expiration = getExpirationDate(n);
		boolean deleted = false;
		List<ChoiceReport> choiceReports = null;

		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblchoices + " WHERE DateCreated <=?;");
			ps.setTimestamp(1, expiration);
			ps.executeUpdate();
			ps.close();
			deleted = true;

		} catch (Exception e) {
			throw new Exception("Failed to delete old choices: " + e.getMessage());
		}
		if (deleted) {
			choiceReports = getAllChoices();
		}
		return choiceReports;
	}

	// completes choice and returns a boolean of if it was successful
	public boolean completeChoice(String choiceID) throws Exception {
		boolean success = false;
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE " + tblchoices + " set isCompleted = ? Where idchoice = ?");
			ps.setInt(1, 1);
			ps.setString(2, choiceID);
			ps.execute();
			success = true;

		} catch (Exception e) {
			success = false;
			throw new Exception("Failed to complete choice: " + e.getMessage());
		}
		return success;
	}

	// checks if the given choice is completed
	// returns true if a choice is completed
	public boolean isCompleted(String choiceID) throws Exception {
		boolean isCompleted = false;
		boolean choiceExists = false;
		int data = 0;
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT isCompleted FROM " + tblchoices + " Where idChoice= ?");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				data = resultSet.getInt("isCompleted");
				choiceExists = true;
			}
			if (!choiceExists) {
				throw new Exception("The choice does NOT exist");
			}
			if (data == 1) {
				isCompleted = true;
			}

		} catch (Exception e) {
			throw new Exception("Failed to find if the choice is completed: " + e.getMessage());
		}
		return isCompleted;
	}

	// gets an alternative object with ID
	// *add reactions and feedback
	public Alternative getAlternativewithID(String ID) throws Exception {
		int counter = 0;
		Alternative alt = null;
		ArrayList<String> approvers = getLikedBy(ID);
		ArrayList<String> disapprovers = getDislikedBy(ID);
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblAlternative + " WHERE idAlternative=?;"); //
			ps.setString(1, ID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				counter++;
				String description = resultSet.getString("alternative");
				// .DAO.String altID = resultSet.getString("idAlternative");
				// ArrayList<String> approvers = getLikedBy(altID);
				// ArrayList<String> disapprovers = getDislikedBy(altID);
				alt = new Alternative(description, ID);
				// alt.setApprovers(approvers);
				// alt.setDisapprovers(disapprovers);
				// alternative= alt;
			}
			System.out.println(counter);
			resultSet.close();
			ps.close();
			alt.setApprovers(approvers);
			alt.setDisapprovers(disapprovers);
			return alt;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting alternative: " + e.getMessage());
		}
	}

	// returns user ID from name and choiceID
	// I could just return user id when we create a user and we may not need this
	// function
	public String getUserID(String username, String choiceID) throws Exception {
		String memberID = "";
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT idTeamMember FROM " + tblTeamMember + " WHERE choiceID =? and name =?;"); //
			ps.setString(1, choiceID);
			ps.setString(2, username);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				memberID = resultSet.getString("idTeamMember");
			}
			resultSet.close();
			ps.close();
			return memberID;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting member ID: " + e.getMessage());
		}
	}

	// returns the name of the user form a given ID
	public String getUserNameWithID(String ID) {
		String name = "";
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT name FROM " + tblTeamMember + " WHERE idTeamMember=?;");
			ps.setString(1, ID);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	// stores a feedback in the database and returns that feedback in object form
	public Feedback giveFeedback(String memberID, String altid, String feedback, String choiceID) throws Exception {
		Feedback fb = null;
		if (isCompleted(choiceID)) {
			throw new Exception("Choice is complete you can't react");
		}
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblFeedback
					+ " (idFeedback, memberID, alternativeID, time, feedback) values(?,?,?,?,?);");
			String newID = UUID.randomUUID().toString();
			ps.setString(1, newID);
			ps.setString(2, memberID);
			ps.setString(3, altid);
			LocalDateTime myTime = LocalDateTime.now();
			Timestamp ts = Timestamp.valueOf(myTime);
			ps.setTimestamp(4, ts);
			ps.setString(5, feedback);
			ps.execute();
			ps.close();

			String name = getUserNameWithID(memberID);
			fb = new Feedback(name, altid, feedback, ts.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting Feedback: " + e.getMessage());
		}
		return fb;
	}

	// given the number of days imputed, will return a Timestamp that is that number
	// of days before the current time
	// used by the function that deletes stale choices
	public Timestamp getExpirationDate(float days) {
		LocalDateTime myTime = LocalDateTime.now();
		Timestamp today = Timestamp.valueOf(myTime);
		float milli = 86400000 * days;
		long milliseconds = (long) milli;
		long time = today.getTime() - milliseconds;
		Timestamp expiration = new Timestamp(time);

		return expiration;
	}

}
