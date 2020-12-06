package httpRequestsAndResponses;

import model.Alternative;

public class SelectApprovalResponse {
	public final Alternative alt ;
	public final int httpCode;
	public final String error;
	
	public SelectApprovalResponse (Alternative alt, int statusCode) {
		this.alt = alt;
		//Might also need alternative similar to the request
		this.httpCode = statusCode;
		this.error = "";
	}
	
	public SelectApprovalResponse (int statusCode, String errorMessage) {
		this.httpCode = statusCode;
		this.error = errorMessage;
		this.alt = null;
	}
	
	public String toString() {
		if (httpCode == 200) {  // too cute?
			return "ApprovalResponse(" + alt.toString() + ")";
		} else {
			return "ErrorResult(" + "statusCode=" + httpCode + ", err=" + error + ")";
		}
	}
}
