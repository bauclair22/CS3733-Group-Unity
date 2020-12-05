package httpRequestsAndResponses;

import model.Choice;

public class ParticipateChoiceResponse {
	public final Choice choice;
	public final String memberID;
	public final int httpCode;
	public final String response; //Is the response a string or is the response the alternatives/feedback for the given choice? 
	
	public ParticipateChoiceResponse(String s, int code) {
		this.choice = null;
		this.memberID= null;
		this.response = s;
		this.httpCode = code;
	}
	
	public ParticipateChoiceResponse(Choice c, String mid) {
		this.choice = c;
		this.memberID = mid;
		this.response = "everything is fine...we fine here";
		this.httpCode = 200;
	}
	
	//May need to edit this if we need to find a better way to display the alternatives/feedback for the choice
	public String toString() {
		if(httpCode == 200) {
			return "Your choice:" + response;
		}
		else {
			return "ErrorResponse(" + response + ")";
		}
	}
}
