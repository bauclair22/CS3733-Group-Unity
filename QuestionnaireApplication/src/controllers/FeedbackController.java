package controllers;

import model.Alternative;
import model.Feedback;

public class FeedbackController {
	boolean success;
	public FeedbackController(Alternative alt) {
		//TODO: add parsing to determine Strings from the HTML request, set success to true if it succeeds, leave false if fails;
		String member="";
		String desc="";
		Feedback f=new Feedback(member, desc);
		alt.addFeedback(f);
        success=false; 
	}
}
