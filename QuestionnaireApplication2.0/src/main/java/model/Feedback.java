package model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Feedback {
	String memberName;
	String altID;
	String description;
	Timestamp timestamp;
	//Calendar calendar;
	
	public Feedback(String memberName, String altID, String description, Timestamp timeStamp2) {
		this.memberName = memberName;
		this.altID= altID;
		this.description = description;
		this.timestamp = timeStamp2;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}
	
	@Override
	public String toString() {
		/*
		return "Alternative [title=" + title + ", AltID=" + AltID + ", approvers=" + approvers + ", disapprovers="
				+ disapprovers + ", feedback=" + feedback + "]";
		*/
		String time = "";
		if(timestamp != null) {time = timestamp.toString();}
		
		return "[" + memberName + description + time + "]";
	}
}
