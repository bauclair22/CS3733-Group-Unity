package model;

public class TeamMember {
	String name;
	String password;
	
	public TeamMember(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	/**
	 * Create a team member without a password
	 * @param username
	 */
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

	/**
	 * Checks to see if the teamMember and their password and username match
	 * @param TeamMember
	 * @return
	 */
	public boolean isEqual(TeamMember teamMember) {
		return this.isEqual(teamMember.getName(), teamMember.getPassword());
	}

	/**
	 * Checks to see the name and password match
	 * @param TeamMember
	 * @return
	 */
	public boolean isEqual(String name, String password) {
		return this.name == name && this.password == password;
	}
	
	@Override
	public String toString() {
		/*
		return "Alternative [title=" + title + ", AltID=" + AltID + ", approvers=" + approvers + ", disapprovers="
				+ disapprovers + ", feedback=" + feedback + "]";
		*/
		return "[" + name + password + "]";
	}
}
