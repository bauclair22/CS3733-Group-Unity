package httpRequestsAndResponses;

public class ParticipateChoiceResponse {
	public final int httpCode;
	public final String response; //Is the response a string or is the response the alternatives/feedback for the given choice? 
	
	public ParticipateChoiceResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public ParticipateChoiceResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	//May need to edit this if we need to find a better way to display the alternatives/feedback for the choice
	public String toString() {
		return "Response(" + response + ")";
	}
}
