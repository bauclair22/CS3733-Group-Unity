package httpRequestsAndResponses;

import model.Alternative;

public class SelectDisapprovalResponse {
	public final Alternative alt;
	public final int httpCode;
	public final String error;
	
	public SelectDisapprovalResponse (Alternative alt, int statusCode) {
		this.alt = alt;
		//Might also need alternative similar to the request
		this.httpCode = statusCode;
		this.error = "";
	}
	
	public SelectDisapprovalResponse ( int statusCode, String errorMessage) {
		this.httpCode = statusCode;
		this.error = errorMessage;
		this.alt =null;
	}
	
	public String toString() {
		if (httpCode == 200) {  // too cute?
			return "DisapprovalResponse(" + alt.toString() + ")";
		} else {
			return "ErrorResult(" + ", statusCode=" + httpCode + ", err=" + error + ")";
		}
	}
}
