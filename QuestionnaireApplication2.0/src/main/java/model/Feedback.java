package model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Feedback {
	String memberName;
	String description;
	Date timestamp;
	Calendar calendar;
	
	public Feedback(String memberName, String description) {
		TimeZone est=TimeZone.getTimeZone("EST");
		this.calendar =Calendar.getInstance(est);
		this.memberName = memberName;
		this.description = description;
		this.timestamp =this.calendar.getTime() ;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescription() {
		return description;
	}
}
