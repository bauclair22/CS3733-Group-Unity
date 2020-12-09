package model;

import java.sql.Timestamp;

public class ChoiceReport {
	String choiceID;
	String choiceDescription;
	boolean isCompleted;
	String dateCompleted;
	
	
	
	public String toString() {
		String date="";
		if(dateCompleted != null) {date= dateCompleted.toString();}
		return "["+ choiceID+", " +choiceDescription+", "+isCompleted+", " + date + "]";

	}



	public ChoiceReport(String choiceID, String choiceDescription, boolean isCompleted,  String dateCompleted) {
		this.choiceID = choiceID;
		this.isCompleted = isCompleted;
		this.choiceDescription=choiceDescription;
		this.dateCompleted = dateCompleted;
	}



	public String getChoiceDescription() {
		return choiceDescription;
	}



	public void setChoiceDescription(String choiceDescription) {
		this.choiceDescription = choiceDescription;
	}



	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}



	public String getChoiceID() {
		return choiceID;
	}



	public void setChoiceID(String choiceID) {
		this.choiceID = choiceID;
	}



	public boolean getIsCompleted() {
		return isCompleted;
	}



	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}



	public String getDateCompleted() {
		return dateCompleted;
	}



	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

}
