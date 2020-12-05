package httpRequestsAndResponses;

import model.Alternative;

public class SelectDisapprovalResponse {
	public final Alternative alt;
	public final int statusCode;
	public final String error;
	
	public SelectDisapprovalResponse (Alternative alt, int statusCode) {
		this.alt = alt;
		//Might also need alternative similar to the request
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public SelectDisapprovalResponse ( int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.alt =null;
	}
	
	public String toString() {
		if (statusCode == 200) {  // too cute?
			return "DisapprovalResponse(" + alt.toString() + ")";
		} else {
			return "ErrorResult(" + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
