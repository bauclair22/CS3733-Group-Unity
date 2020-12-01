package model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Feedback {
	String memberName;
	String description;
	Timestamp timestamp;
	Calendar calendar;
	
	public Feedback(String memberName, String description, Timestamp timeStamp2) {
		TimeZone est=TimeZone.getTimeZone("EST");
		this.calendar =Calendar.getInstance(est);
		this.memberName = memberName;
		this.description = description;
		this.timestamp = timeStamp2;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescription() {
		return description;
	}
}
