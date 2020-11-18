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
	
	Choice(String description, Alternative[] alts, int numMembers){
		this.description = description;
		this.alternatives = alts;
		this.numMembers = numMembers;
		this.isCompleted = false;
		//Need something here to randomly assign a unique id number
	}
	
	//Add a TeamMember to the list of TeamMembers as long as the list isn't already at max size
	//Return false if unsuccessful
	boolean addTeamMember(TeamMember t){
		if(teamMembers.size() < numMembers) {
			teamMembers.add(t);
			return true;
		}
		return false;
	}
	
	int getID() {
		return this.idNumber;
	}
	
	boolean getIsCompleted() {
		return this.isCompleted;
	}
	void setIsCompleted(boolean flag) {
		this.isCompleted = flag;
	}
	
	float getDateCompleted() {
		return this.dateCompleted;
	}
	void setDateCompleted(float d) {
		this.dateCompleted = d;
	}
}