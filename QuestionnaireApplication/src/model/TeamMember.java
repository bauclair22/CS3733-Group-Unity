package model;

public class TeamMember {
	String name;
	String password;
	
	TeamMember(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	String getName() {
		return this.name;
	}
}
