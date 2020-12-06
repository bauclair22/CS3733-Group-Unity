package model;

import java.sql.Timestamp;

public class ChoiceReport {
	String choiceID;
	boolean isCompleted;
	String dateCompleted;
	
	
	
	public String toString() {
		String date="";
		if(dateCompleted != null) {date= dateCompleted.toString();}
		return "["+ choiceID +isCompleted + date + "]";

	}



	public ChoiceReport(String choiceID, boolean isCompleted, String dateCompleted) {
		this.choiceID = choiceID;
		this.isCompleted = isCompleted;
		this.dateCompleted = dateCompleted;
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
