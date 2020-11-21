package model;

import java.util.ArrayList;

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
		//Need to fix this to take into account current date
		for(Choice c: choices) {
			if(c.getDateCompleted() > n) {
				choices.remove(c);
			}
		}
	}
}
