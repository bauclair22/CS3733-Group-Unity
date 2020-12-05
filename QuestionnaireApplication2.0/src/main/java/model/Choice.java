package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class Choice {
	String description;
	Alternative[] alternatives = new Alternative[5];
	int numMembers;
	ArrayList<String> teamMembers = new ArrayList<>();
	String choiceID;
	boolean isCompleted;
	Timestamp dateCompleted;
	
	public Choice(String ID, String description, Alternative[] alts, int numMembers){
		this.description = description;
		this.alternatives = alts; //Should this be changed to a list of strings of titles and descriptions to form the alternatives?
		this.numMembers = numMembers;
		this.isCompleted = false;
		this.choiceID = ID;
	}
	
	
	
	/**
	 * Add a TeamMember to the list of TeamMembers as long as the list isn't already at max size
	 * @param TeamMembert
	 * @return Return false if unsuccessful
	 */
	public boolean addTeamMember(String t){
		if(teamMembers.size() < numMembers) {
			teamMembers.add(t);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Add an Alternative to the array of alternatives to the next empty spot, if there are already 5 alternatives nothing will be added
	 * @param Alternative a
	 * @return false if unsuccessful
	 */
	public boolean addAlternative(Alternative a) {
		boolean added = false;
		for(int i=0; i<5; i++) {
			if(this.alternatives[i]== null) {
				this.alternatives[i]= a;
				added = true;
				i = 5;
			}
		}
		return added;
	}
	
	
	public String getID() {
		return this.choiceID;
	}
	
	public boolean getIsCompleted() {
		return this.isCompleted;
	}
	public void setIsCompleted(boolean flag) {
		this.isCompleted = flag;
	}
	
	public Timestamp getDateCompleted() {
		return this.dateCompleted;
	}
	public void setDateCompleted(Timestamp t) {
		this.dateCompleted = t;
	}



	public String getdescription() {
		return this.description;
	}

	public int getnumMembers() {
		return this.numMembers;
	}

	
	public String printAternativeTitles(){
		String output ="";
		String s = "";
		for(int i =0; i<this.alternatives.length; i++) {
			s= alternatives[i].title;
			output = output.concat(s);
			output = output.concat(", ");
		}
		System.out.printf("Alternative Titles: " +output);
		return output;
	}



	public Alternative[] getAlternatives() {
		return alternatives;
	}

	@Override
	public String toString() {
		/*
		return "Choice [description=" + description + ", alternatives=" + Arrays.toString(alternatives)
				+ ", numMembers=" + numMembers + ", teamMembers=" + teamMembers + ", idNumber=" + idNumber
				+ ", isCompleted=" + isCompleted + ", dateCompleted=" + dateCompleted + "]";
		*/
		return "[" + description + Arrays.toString(alternatives)
		+ numMembers + teamMembers + choiceID
		+ isCompleted + dateCompleted + "]";

	}
}
