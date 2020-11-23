package model;

public class TeamMember {
	String name;
	String password;
	
	public TeamMember(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int createChoice(String description, Alternative[] alts, int numMembers) {
		Choice c = new Choice(description, alts, numMembers);
		return c.idNumber;
	}
}
