package model;

public class TeamMember {
	String name;
	String password;
	
	public TeamMember(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	
	public TeamMember(String name){
		this.name = name;
		this.password = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int createChoice(String description, Alternative[] alts, int numMembers) {
		Choice c = new Choice(description, alts, numMembers);
		return c.idNumber;
	}


	public boolean isEqual(TeamMember m) {
		return this.isEqual(m.getName(), m.getPassword());
	}


	public boolean isEqual(String name, String password) {
		return this.name == name && this.password == password;
	}
}
