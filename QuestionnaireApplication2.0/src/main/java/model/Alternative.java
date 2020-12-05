package model;

import java.util.ArrayList;

public class Alternative {
	String title;
	String AltID;
	ArrayList<String> approvers = new ArrayList<>();
	ArrayList<String> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	
	public Alternative(String title, String altID) {
		this.title = title;
		this.AltID = altID;
	}

	public String getTitle() {
		return title;
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

	public ArrayList<String> getDisapprovers() {
		return disapprovers;
	}

	public void addDisapprover(String disapprover) {
		this.disapprovers.add(disapprover);
	}
	public void removeDisapprover(String disapprover) {
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

	public void setDisapprovers(ArrayList<String> disapprovers) {
		this.approvers = disapprovers;
	}

	@Override
	public String toString() {
		/*
		return "Alternative [title=" + title + ", AltID=" + AltID + ", approvers=" + approvers + ", disapprovers="
				+ disapprovers + ", feedback=" + feedback + "]";
		*/
		return "[" + title + AltID + approvers + disapprovers + feedback + "]";
	}
	
	
	
	
	
}
