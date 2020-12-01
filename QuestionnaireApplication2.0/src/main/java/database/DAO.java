package database;
//import java.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import model.Choice;
import model.Alternative;
import model.Feedback;
import model.TeamMember;

public class DAO {
    java.sql.Connection conn;

    final String tblchoices = "Choices";   // Exact capitalization
    final String tblAlternative = "Alternatives";
    final String tblTeamMember = "TeamMember";
    final String viewFeedbacksWithName = "feedback_withName";
    final String viewLikedBy = "Likedby";
    final String viewDislikedBy = "Dislikedby";
    final String tblReactions = "Reactions";
    final String viewCheckMembers = "checkMembers";

    public DAO() {
        try  {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
        }
    }

    public Choice getChoiceswithID(int ID) throws Exception {

        try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblchoices + " WHERE idChoice=?;");
            ps.setInt(1,  ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int maxUsers = resultSet.getInt("maxUsers");
                String description = resultSet.getString("description");
                Alternative[] alternatives = getChoiceAlternatives(ID);
                choice = new Choice(description, alternatives, maxUsers);
            }
            resultSet.close();
            ps.close();

            return choice;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public Alternative[] getChoiceAlternatives(int choiceid) throws Exception {

        try {
            Alternative A[] = new Alternative[5];
            int counter =0;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblAlternative + " WHERE idChoice=?;");
            ps.setInt(1, choiceid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("alternative");
                int altID = resultSet.getInt("idAltenative");
                Alternative alt = new Alternative(title,altID);
                ArrayList<TeamMember> approvers = getLikedBy(altID);
                ArrayList<TeamMember> disapprovers = getDislikedBy(altID);
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
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<TeamMember> getLikedBy(int altid) throws Exception {

        try {
            ArrayList<TeamMember> approvers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewLikedBy + " WHERE alternativeID=?;");
            ps.setInt(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String pass = resultSet.getString("password");
                TeamMember member=new TeamMember(name,pass);
                approvers.add(member);
            }
            resultSet.close();
            ps.close();

            return approvers;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<TeamMember> getDislikedBy(int altid) throws Exception {

        try {
            ArrayList<TeamMember> disapprovers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewDislikedBy + " WHERE alternativeID=?;");
            ps.setInt(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String pass = resultSet.getString("password");
                TeamMember member=new TeamMember(name,pass);
                disapprovers.add(member);
            }
            resultSet.close();
            ps.close();

            return disapprovers;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<Feedback> getAlternativesFeedback(int altid) throws Exception {

        try {
            ArrayList<Feedback> feedback = new ArrayList<>();
            int counter =0;
            PreparedStatement ps = conn.prepareStatement("SELECT time, feedback, name FROM " + viewFeedbacksWithName + " WHERE alternativeID=?;");
            ps.setInt(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Timestamp time = resultSet.getTimestamp("time");
                String comment = resultSet.getString("feedback");
                String name = resultSet.getString("name");
                Feedback f = new Feedback(name,comment,time);
                feedback.add(f);
            }
            resultSet.close();
            ps.close();

            return feedback;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }


    public String createChoice(int maxUsers, String description, String[] titleAlt) throws Exception {
    	String newID =null;
    	boolean flag = true;
        try {
        	System.out.printf("trying to create choice" + "maxUsers: " + maxUsers + "description" + description);
            String query = "INSERT INTO " + tblchoices + " (idChoice, maxUsers, description) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            newID = UUID.randomUUID().toString();
            ps.setString(1, newID);
            ps.setInt(2, maxUsers);
            ps.setString(3, description);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
        	flag =false;
        	newID = null;
        	System.out.println(" failed to create choice ");
            throw new Exception("Failed to update report: " + e.getMessage());
        }
        
        if(flag) {
        	for(int i=0; i<titleAlt.length; i++) {
        		createAlternative(titleAlt[i], newID);
        	}
        }
        
        return newID;
    }
    
    public String createAlternative(String title, String choiceID) throws Exception {
    	String newID;
        try {
            String query = "INSERT INTO " + tblAlternative + " (idAlternative, choiceID, alternative, description) VALUES (?, ?, ?, ?);";
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
    
    public boolean addUser(String Username, String Password, String choiceid) throws Exception {

    	boolean flagMatchFound = false;
    	boolean added =false;
    	if(canAdd(choiceid)) {
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblTeamMember + " WHERE name=? AND choiceID=?;");
	            ps.setString(1,Username);
	            ps.setString(2,choiceid);
	            ResultSet resultSet = ps.executeQuery();
	
	            while (resultSet.next()) {
	            	flagMatchFound = true;
	                String name = resultSet.getString("name");
	                String password = resultSet.getString("password");
	                if(Password != password) {
	                	 throw new Exception("Password is incorrect");
	                }
	            }
	            resultSet.close();
	            ps.close();
	
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("Failed in getting constant: " + e.getMessage());
	        }
	        if(!flagMatchFound) {
	        	try {
	                String query = "INSERT INTO " + tblTeamMember + "  VALUES (?, ?, ?, ?);";
	                String newMemberID = UUID.randomUUID().toString();
	                PreparedStatement ps = conn.prepareStatement(query);
	                ps.setString(1, newMemberID);
	                ps.setString(2, choiceid);
	                ps.setString(3, Username);
	                ps.setString(4, Password);
	                ps.executeUpdate();
	                ps.close();
	                added = true;
	
	            } catch (Exception e) {
	                throw new Exception("Failed to update report: " + e.getMessage());
	            }
	        }
    	}
        return added;
    }
    
    public boolean canAdd(String ChoiceID) throws Exception {
    	boolean canadd = false;
    	int participating=0;
    	int max = 0;
    	try {
            PreparedStatement ps = conn.prepareStatement("SELECT count(idTeamMember) as participating, maxUsers FROM " + tblTeamMember + " join "+ tblchoices + " WHERE choiceID = idChoice AND choiceID=?;");
            ps.setString(1, ChoiceID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                participating = resultSet.getInt("participating");
                max = resultSet.getInt("maxUsers");
                System.out.printf("max: " + max + " participating: " + participating);
             
            }
            if(max > participating || max==0 ) {
            	canadd= true;
            }else {
            	throw new Exception("Choice is full: No more users allowed");
            }
            resultSet.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    	return canadd;
    }
    
    public boolean deleteApprover(String Username, int altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + viewLikedBy +  "WHERE name=? AND alternativeID=?;"); //Not sure if this is entirely correct
            //Should we clear approval for all alternatives in the choice or just one specific alternative in the choice?
            ps.setString(1, Username);
            ps.setInt(2, altid);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete approver: " + e.getMessage());
        }
    }
    
    public boolean deleteDisapprover(String Username, int altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + viewDislikedBy +  "WHERE name=? AND alternativeID=?;"); //Not sure if this is entirely correct
            //Should we clear approval for all alternatives in the choice or just one specific alternative in the choice?
            ps.setString(1, Username);
            ps.setInt(2, altid);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete disapprover: " + e.getMessage());
        }
    }
    
    public List<Choice> getAllChoices() throws Exception {
        
        List<Choice> allChoices = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + tblchoices + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Choice c = generateChoice(resultSet);
                allChoices.add(c);
            }
            resultSet.close();
            statement.close();
            return allChoices;

        } catch (Exception e) {
            throw new Exception("Failed in getting choices: " + e.getMessage());
        }
    }
    
    private Choice generateChoice(ResultSet resultSet) throws Exception {
    	String description = resultSet.getString("description");
    	//Need something here to create the alternatives in the choice or need to change the choice constructor
    	Alternative[] alternatives = new Alternative[5];
		int numMembers = resultSet.getInt("maxUsers"); 
        return new Choice (description, alternatives, numMembers);
    }
    
    public boolean addApprover(String Username, int altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + viewLikedBy + "WHERE name=? AND alternativeID=?;");
            ps.setString(1, Username);
            ps.setInt(2, altid);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }
            //Check if present in dislikes 
            ps = conn.prepareStatement("SELECT * FROM " + viewDislikedBy + "WHERE name=? AND alternativeID=?;");
            ps.setString(1, Username);
            ps.setInt(2, altid);
            resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + viewLikedBy + " (name) values(?);"); //Someone should doublecheck this part
            ps.setString(1, Username);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert approver: " + e.getMessage());
        }
    }

    public boolean addDisapprover(String Username, int altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + viewDislikedBy + "WHERE name=? AND alternativeID=?;");
            ps.setString(1, Username);
            ps.setInt(2, altid);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }
            //Check if present in likes 
            ps = conn.prepareStatement("SELECT * FROM " + viewLikedBy + "WHERE name=? AND alternativeID=?;");
            ps.setString(1, Username);
            ps.setInt(2, altid);
            resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + viewDislikedBy + " (name) values(?);"); //Someone should doublecheck this part
            ps.setString(1, Username);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert disapprover: " + e.getMessage());
        }
    }
    
    //not done
    public boolean deleteStaleChoices(Timestamp expiration) throws Exception { //might need to delete the alternatiives and teamMembers asociated wit this
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblchoices +  "WHERE DateCompleted > ?;"); //Not sure if this is entirely correct
            //Should we clear approval for all alternatives in the choice or just one specific alternative in the choice?
            ps.setTimestamp(1, expiration);
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to delete old choices: " + e.getMessage());
        }
    }
    
    public boolean completeChoice(Timestamp expiration, String choiceID) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + tblchoices + " set DateCompleted = ? Where idchoice = ?"); 
            ps.setTimestamp(1, expiration);
            ps.setString(2, choiceID);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to compleate choice: " + e.getMessage());
        }
    }
    
    //create feedback function

}


