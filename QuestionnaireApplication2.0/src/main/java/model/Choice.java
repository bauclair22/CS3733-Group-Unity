package model;

import java.util.ArrayList;

public class Choice {
	String description;
	Alternative[] alternatives = new Alternative[5];
	int numMembers;
	ArrayList<TeamMember> teamMembers = new ArrayList<>();
	int idNumber;
	boolean isCompleted;
	float dateCompleted;
	
	public Choice(String description, Alternative[] alts, int numMembers){
		this.description = description;
		this.alternatives = alts; //Should this be changed to a list of strings of titles and descriptions to form the alternatives?
		this.numMembers = numMembers;
		this.isCompleted = false;
		//Need something here to randomly assign a unique id number
	}
	
	//Add a TeamMember to the list of TeamMembers as long as the list isn't already at max size
	//Return false if unsuccessful
	public boolean addTeamMember(TeamMember t){
		if(teamMembers.size() < numMembers) {
			teamMembers.add(t);
			return true;
		}
		return false;
	}
	
	//Add an Alternative to the array of alternatives to the next empty spot, if there are already 5 alternatives nothing will be added
	//return false if unsuccessful
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
	
	
	public int getID() {
		return this.idNumber;
	}
	
	public boolean getIsCompleted() {
		return this.isCompleted;
	}
	public void setIsCompleted(boolean flag) {
		this.isCompleted = flag;
	}
	
	public float getDateCompleted() {
		return this.dateCompleted;
	}
	public void setDateCompleted(float d) {
		this.dateCompleted = d;
	}
}
