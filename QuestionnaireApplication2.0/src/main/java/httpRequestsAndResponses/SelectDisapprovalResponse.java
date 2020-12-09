package httpRequestsAndResponses;

import model.Alternative;
import model.Choice;

public class SelectDisapprovalResponse {
	public final Choice choice;
	public final int httpCode;
	public final String error;
	
	public SelectDisapprovalResponse (Choice choice, int statusCode) {
		this.choice = choice;
		//Might also need alternative similar to the request
		this.httpCode = statusCode;
		this.error = "";
	}
	
	public SelectDisapprovalResponse ( int statusCode, String errorMessage) {
		this.httpCode = statusCode;
		this.error = errorMessage;
		this.choice =null;
	}
	
	public String toString() {
		if (httpCode == 200) {  // too cute?
			return "DisapprovalResponse(" + choice.toString() + ")";
		} else {
			return "ErrorResult(" + ", statusCode=" + httpCode + ", err=" + error + ")";
		}
	}
}
