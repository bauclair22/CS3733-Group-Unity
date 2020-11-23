package model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Feedback {
	String memberName;
	String description;
	Timestamp timestamp;
	
	public Feedback(String memberName, String description, Timestamp time) {
		this.memberName = memberName;
		this.description = description;
		this.timestamp = time;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescription() {
		return description;
	}
}
