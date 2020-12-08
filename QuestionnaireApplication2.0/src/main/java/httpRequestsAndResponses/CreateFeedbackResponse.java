package httpRequestsAndResponses;

import model.Feedback;

public class CreateFeedbackResponse {
	public Feedback response;
	public String errorMessage;
	public final int httpCode;
	
	public CreateFeedbackResponse (String errorMessage, int code) {
		this.response = null;
		this.httpCode = code;
		this.errorMessage = errorMessage;
	}
	
	public CreateFeedbackResponse (Feedback f) {
		this.response = f;
		this.httpCode = 200;
		this.errorMessage = "";
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
