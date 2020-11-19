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
}
