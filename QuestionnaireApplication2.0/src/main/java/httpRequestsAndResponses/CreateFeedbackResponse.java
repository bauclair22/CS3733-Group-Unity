package httpRequestsAndResponses;

import model.Choice;
import model.Feedback;

public class CreateFeedbackResponse {
	public Choice choice;
	public String errorMessage;
	public final int httpCode;
	
	public CreateFeedbackResponse (String errorMessage, int code) {
		this.choice = null;
		this.httpCode = code;
		this.errorMessage = errorMessage;
	}
	
	public CreateFeedbackResponse (Choice c) {
		this.choice = c;
		this.httpCode = 200;
		this.errorMessage = "";
	}
	
	public String toString() {
		return "Response(" + choice + ")";
	}
}
