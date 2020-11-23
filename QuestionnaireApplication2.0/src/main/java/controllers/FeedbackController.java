package controllers;

import java.sql.Timestamp;

import model.Alternative;
import model.Feedback;

public class FeedbackController {
	boolean success;
	public FeedbackController(Alternative alt) {
		//TODO: add parsing to determine Strings from the HTML request, set success to true if it succeeds, leave false if fails;
		String member="";
		String desc="";
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Feedback f=new Feedback(member, desc, time );
		alt.addFeedback(f);
        success=false; 
	}
}
