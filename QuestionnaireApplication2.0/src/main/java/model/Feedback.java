package model;

public class Feedback {
	String memberName;
	String description;
	//Needs timestamp
	
	public Feedback(String memberName, String description) {
		this.memberName = memberName;
		this.description = description;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescription() {
		return description;
	}
}
