package model;

import java.util.ArrayList;

public class Alternative {
	String title;
	int AltID;
	String description;
	ArrayList<String> approvers = new ArrayList<>();
	ArrayList<TeamMember> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	
	public Alternative(String title, String description, int altID) {
		this.title = title;
		this.description = description;
		this.AltID = altID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getApprovers() {
		return approvers;
	}

	public void addApprover(String approver) {
		this.approvers.add(approver);
	}
	public void removeApprover(String approver) {
		this.approvers.remove(approver);
	}

	public ArrayList<TeamMember> getDisapprovers() {
		return disapprovers;
	}

	public void addDisapprover(TeamMember disapprover) {
		this.disapprovers.add(disapprover);
	}
	public void removeDisapprover(TeamMember disapprover) {
		this.disapprovers.remove(disapprover);
	}

	public ArrayList<Feedback> getFeedback() {
		return feedback;
	}

	public void addFeedback(Feedback feedback) {
		this.feedback.add(feedback);
	}
	
	public void removeFeedback(Feedback feedback) {
		this.feedback.remove(feedback);
	}

	public void setApprovers(ArrayList<String> approvers) {
		this.approvers = approvers;
		
	}

	public void setFeedback(ArrayList<Feedback> feedback) {
		this.feedback = feedback;
		
	}

	public void setDispprovers(ArrayList<String> disapprovers) {
		this.approvers = disapprovers;
	}
	
	
}
