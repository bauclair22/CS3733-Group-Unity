package model;

import java.util.ArrayList;

public class Alternative {
	String title;
	String description;
	ArrayList<TeamMember> approvers = new ArrayList<>();
	ArrayList<TeamMember> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	
	public Alternative(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<TeamMember> getApprovers() {
		return approvers;
	}

	public void addApprover(TeamMember approver) {
		this.approvers.add(approver);
	}
	public void removeApprover(TeamMember approver) {
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
	
	
}
