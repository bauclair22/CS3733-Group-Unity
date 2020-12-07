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

    final String tblchoices = "Choices";   // Exact capitalization
    final String tblAlternative = "Alternatives";
    final String tblTeamMember = "TeamMember";
    final String viewFeedbacksWithName = "feedback_withName";
    final String tblFeedback ="Feedbacks";
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

    public Choice getChoiceswithID(String ID) throws Exception {

        try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblchoices + " WHERE idChoice=?;"); //
            ps.setString(1,  ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int maxUsers = resultSet.getInt("maxUsers");
                String description = resultSet.getString("description");
                Timestamp time = resultSet.getTimestamp("DateCreated");
                Alternative[] alternatives = getChoiceAlternatives(ID);
                choice = new Choice(ID, description, alternatives, maxUsers, time);
            }
            resultSet.close();
            ps.close();

            return choice;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public Alternative[] getChoiceAlternatives(String choiceid) throws Exception {

        try {
            Alternative A[] = new Alternative[5];
            int counter =0;
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
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<String> getLikedBy(String altid) throws Exception {

        try {
            ArrayList<String> approvers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewLikedBy + " WHERE alternativeID=?;");
            ps.setString(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                //String pass = resultSet.getString("password");
                //TeamMember member=new TeamMember(name,pass);
                approvers.add(name);
            }
            resultSet.close();
            ps.close();

            return approvers;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<String> getDislikedBy(String altid) throws Exception {

        try {
            ArrayList<String> disapprovers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewDislikedBy + " WHERE alternativeID=?;");
            ps.setString(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                //String pass = resultSet.getString("password");
                //TeamMember member=new TeamMember(name,pass);
                disapprovers.add(name);
            }
            resultSet.close();
            ps.close();

            return disapprovers;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    public ArrayList<Feedback> getAlternativesFeedback(String altid) throws Exception {

        try {
            ArrayList<Feedback> feedback = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT time, feedback, name FROM " + viewFeedbacksWithName + " WHERE alternativeID=?;");
            ps.setString(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Timestamp time = resultSet.getTimestamp("time");
                String comment = resultSet.getString("feedback");
                String name = resultSet.getString("name");
                Feedback f = new Feedback(name,altid, comment,time);
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
            String query = "INSERT INTO " + tblchoices + " (idChoice, maxUsers, description, DateCreated, isCompleted) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            newID = UUID.randomUUID().toString();
            ps.setString(1, newID);
            ps.setInt(2, maxUsers);
            ps.setString(3, description);
            LocalDateTime myTime = LocalDateTime.now();
    		Timestamp ts= Timestamp.valueOf(myTime);
            ps.setTimestamp(4, ts);
            ps.setInt(5, 0);
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
            String query = "INSERT INTO " + tblAlternative + " (idAlternative, choiceID, alternative) VALUES (?, ?, ?);";
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
    
    public boolean addUser(String Username, String password, String choiceid) throws Exception {

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
	                String correctPassword = resultSet.getString("password");
	                if(correctPassword.contentEquals(password)) {
	                	 added= true;
	                }else {throw new Exception("Password is NOT NOT incorrect");}
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
	                ps.setString(4, password);
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
    
    
    public boolean deleteReaction(String memberID, String altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblReactions +  " WHERE memberID=? AND alternativeID=?;"); 
            ps.setString(1, memberID);
            ps.setString(2, altid);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete reaction: " + e.getMessage());
        }
    }
    
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
    
    private ChoiceReport generateChoice(ResultSet resultSet) throws Exception {
    	//String description = resultSet.getString("description");
    	//Need something here to create the alternatives in the choice or need to change the choice constructor
    	//Alternative[] alternatives = new Alternative[5];
		//int numMembers = resultSet.getInt("maxUsers"); 
    	boolean isCompleted = resultSet.getBoolean("isCompleted");
		String ID = resultSet.getString("idChoice");
		Timestamp date = resultSet.getTimestamp("DateCreated");
		String s = null;
		if(date != null) {
			s = date.toString();
		}
        return new ChoiceReport (ID, isCompleted, s);
    }
    
    
   public boolean addApprover(String memberID, String altid) throws Exception {
	   try {
		    PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblReactions + " WHERE memberID = ? AND alternativeID = ?;");
            ps.setString(1, memberID);
            ps.setString(2, altid);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + tblReactions + " (idReaction, alternativeID, memberID, reaction) values(?,?,?,?);"); 
            String newID = UUID.randomUUID().toString();
            ps.setString(1, newID);
            ps.setString(2, altid);
            ps.setString(3, memberID);
            ps.setString(4, "Like");
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert disapprover: " + e.getMessage());
        }
    }

    public boolean addDisapprover(String memberID, String altid) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblReactions + " WHERE memberID =? AND alternativeID =?;");
            ps.setString(1, memberID);
            ps.setString(2, altid);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + tblReactions + " (idReaction, alternativeID, memberID, reaction) values(?,?,?,?);"); 
            String newID = UUID.randomUUID().toString();
            ps.setString(1, newID);
            ps.setString(2, altid);
            ps.setString(3, memberID);
            ps.setString(4, "Dislike");
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert disapprover: " + e.getMessage());
        }
    }
    
    //when old choices are deleted do they have to have been completed?
    public List<ChoiceReport> deleteStaleChoices(Timestamp expiration) throws Exception { 
    	boolean deleted = false;
    	List<ChoiceReport> choiceReports = null;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblchoices +  "WHERE DateCreated > ?;"); 
            ps.setTimestamp(1, expiration);
            ps.executeUpdate();
            ps.close();
            deleted= true;

        } catch (Exception e) {
            throw new Exception("Failed to delete old choices: " + e.getMessage());
        }
         if(deleted) {
        	 choiceReports =getAllChoices();
        }
         return choiceReports;
    }
    
    public boolean completeChoice( String choiceID) throws Exception {
        try {
        	PreparedStatement ps = conn.prepareStatement("UPDATE " + tblchoices + " set isCompleted = ? Where idchoice = ?"); 
            ps.setInt(1, 1);
            ps.setString(2, choiceID);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to compleate choice: " + e.getMessage());
        }
    }
    
    
    public Alternative getAlternativewithID(String ID) throws Exception {
    	int counter =0;
    	Alternative alternative = null;
    	Alternative alt = null;
    	ArrayList<String>  approvers = getLikedBy(ID);
        ArrayList<String>  disapprovers = getDislikedBy(ID);
    	try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblAlternative + " WHERE idAlternative=?;"); //
            ps.setString(1,  ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	counter++;
                String description = resultSet.getString("alternative");
                //.DAO.String altID = resultSet.getString("idAlternative");
                //ArrayList<String>  approvers = getLikedBy(altID);
                //ArrayList<String>  disapprovers = getDislikedBy(altID);
                alt = new Alternative(description, ID);
                //alt.setApprovers(approvers);
                //alt.setDisapprovers(disapprovers);
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
    
    public String getUserID(String username, String choiceID) throws Exception {
    	String memberID="";
    	try {
            PreparedStatement ps = conn.prepareStatement("SELECT idTeamMember FROM " + tblTeamMember + " WHERE choiceID =? and name =?;"); //
            ps.setString(1,  choiceID);
            ps.setString(2,  username);
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
    
    public String getUserNameWithID(String ID) {
    	String name="";
         try {
        	 PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + tblTeamMember+ " WHERE idTeamMember=?;");
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
    
    public Feedback giveFeedback(String memberID,String altid, String feedback) throws Exception {
    	Feedback fb = null;
        try {
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblFeedback + " (idFeedback, memberID, alternativeID, time, feedback) values(?,?,?,?,?);"); 
             String newID = UUID.randomUUID().toString();
             ps.setString(1, newID);
             ps.setString(2, memberID);
             ps.setString(3, altid);
             LocalDateTime myTime = LocalDateTime.now();
     		 Timestamp ts= Timestamp.valueOf(myTime);
             ps.setTimestamp(4, ts);
             ps.setString(5,feedback);
             ps.execute();
             ps.close();
             
             String name= getUserNameWithID(memberID);
             fb = new Feedback(name,altid,feedback, ts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting Feedback: " + e.getMessage());
        }
        return fb;
    }

}


