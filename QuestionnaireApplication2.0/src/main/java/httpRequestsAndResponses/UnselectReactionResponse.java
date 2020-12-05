package httpRequestsAndResponses;

import model.Alternative;

public class UnselectReactionResponse {
	public final Alternative alt;
	public final int statusCode;
	public final String error;
	
	public UnselectReactionResponse ( Alternative alt, int statusCode) {
		this.alt = alt;
		//Might also need alternative similar to the request
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public UnselectReactionResponse ( int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.alt = null;
	}
	
	public String toString() {
		if (statusCode == 200) {  // too cute?
			return "UnselectResponse(" + alt.toString() + ")";
		} else {
			return "ErrorResult(" + "statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
