package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Admin {
	ArrayList<Choice> choices = new ArrayList<>();

	public Admin() {
	}

	public ArrayList<Choice> getChoices() {
		return choices;
	}

	public void addChoices(Choice choice) {
		this.choices.add(choice);
	}
	
	public void deleteOld(float n) {
		//Make sure works
		Calendar cal=Calendar.getInstance();
		TimeZone est=TimeZone.getTimeZone("EST");
		cal.setTimeZone(est);// Should set calendar to today
		int mins= (int) Math.floor(n/1440.0);// finds how many minutes to go back in time
		cal.add(Calendar.MINUTE, -1*mins);// Sets calendar BACKWARDS that many minutes
		Date purge=cal.getTime();// gets time (current time minus that many minutes
		
		for(Choice c: choices) {
			if(c.getDateCompleted().before(purge)); {// IF DATE COMPLETED IS BEFORE PURGE DATE
				choices.remove(c);
			}
		}
	}
}
