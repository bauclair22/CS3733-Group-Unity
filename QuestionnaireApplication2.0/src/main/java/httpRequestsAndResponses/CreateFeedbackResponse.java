package httpRequestsAndResponses;

import model.Choice;
import model.Feedback;

public class CreateFeedbackResponse {
	public Choice response;
	public String errorMessage;
	public final int httpCode;
	
	public CreateFeedbackResponse (String errorMessage, int code) {
		this.response = null;
		this.httpCode = code;
		this.errorMessage = errorMessage;
	}
	
	public CreateFeedbackResponse (Choice c) {
		this.response = c;
		this.httpCode = 200;
		this.errorMessage = "";
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
