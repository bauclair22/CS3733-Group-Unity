package database;
//import java.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import model.Choice;
import model.Alternative;
import model.Feedback;
import model.TeamMember;

public class DAO {
    java.sql.Connection conn;

    final String tblchoices = "Choices";   // Exact capitalization
    final String tblAlternative = "Alternatives";
    final String tblTeamMember = "TeamMembers";
    final String viewFeedbacksWithName = "feedback_withName";
    final String viewLikedBy = "Likedby";
    final String viewDislikedBy = "Dislikedby";
    final String tblReactions = "Reactions";

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
                String description = resultSet.getString("description");
                int altID = resultSet.getInt("idAltenative");
                Alternative alt = new Alternative(title, description, altID);
                ArrayList<String> approvers = getLikedBy(altID);
                ArrayList<String> disapprovers = getDislikedBy(altID);
                ArrayList<Feedback> feedback = getAlternativesFeedback(altID);
                alt.setApprovers(approvers);
                alt.setDispprovers(disapprovers);
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

    public ArrayList<String> getLikedBy(int altid) throws Exception {

        try {
            ArrayList<String> approvers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewLikedBy + " WHERE alternativeID=?;");
            ps.setInt(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
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

    public ArrayList<String> getDislikedBy(int altid) throws Exception {

        try {
            ArrayList<String> disapprovers = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + viewDislikedBy + " WHERE alternativeID=?;");
            ps.setInt(1, altid);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
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


    public int createChoice(int maxUsers, String description) throws Exception {
        try {
            String query = "INSERT INTO " + tblchoices + " (idChoice, maxUsers, description) VALUES (seq_Choices.nextval, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, maxUsers);
            ps.setString(2, description);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }

        try {
            int id = 0;
            PreparedStatement ps = conn.prepareStatement("SELECT LAST_INSERT_ID() as id;");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
            ps.close();

            return id;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean addUser(String Username, String Password, int choiceid) throws Exception {

    	boolean flagMatchFound = false;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM" + tblTeamMember + " WHERE name=? AND choiceID=?;");
            ps.setString(1,Username);
            ps.setInt(2,choiceid);
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
                String query = "INSERT INTO " + tblTeamMember + "  VALUES (seq_TeamMember.nextval, ?, ?, ?);";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, choiceid);
                ps.setString(2, Username);
                ps.setString(2, Password);
                ps.executeQuery();
                ps.close();

            } catch (Exception e) {
                throw new Exception("Failed to update report: " + e.getMessage());
            }
        }
        return flagMatchFound;
    }


}


