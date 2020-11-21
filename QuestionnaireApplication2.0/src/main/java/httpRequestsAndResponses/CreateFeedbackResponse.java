package httpRequestsAndResponses;

public class CreateFeedbackResponse {
	public String response;
	public final int httpCode;
	
	public CreateFeedbackResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public CreateFeedbackResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
