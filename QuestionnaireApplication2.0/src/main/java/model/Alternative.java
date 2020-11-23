package model;

import java.util.ArrayList;

public class Alternative {
	int id;
	String title;
	String description;
	ArrayList<String> approvers = new ArrayList<>();
	ArrayList<String> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	
	public Alternative(String title, String description, int id) {
		this.title = title;
		this.description = description;
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

	public void addApprover(TeamMember approver) {
		this.approvers.add(approver.name);
	}
	public void setApprovers(ArrayList<String> approvers) {
		this.approvers= approvers;
	}
	public void removeApprover(TeamMember approver) {
		this.approvers.remove(approver.name);
	}

	public ArrayList<String> getDisapprovers() {
		return disapprovers;
	}

	public void addDispprover(TeamMember disapprover) {
		this.disapprovers.add(disapprover.name);
	}
	
	public void setDisapprovers(ArrayList<String> disapprovers) {
		this.disapprovers = disapprovers;
	}
	
	public void removeDisapprover(TeamMember disapprover) {
		this.disapprovers.remove(disapprover.name);
	}

	public ArrayList<Feedback> getFeedback() {
		return feedback;
	}

	public void addFeedback(Feedback feedback) {
		this.feedback.add(feedback);
	}
	
	public void setFeedback(ArrayList<Feedback> feedback) {
		this.feedback = feedback;
	}
	
	public void removeFeedback(Feedback feedback) {
		this.feedback.remove(feedback);
	}
	
	
}
