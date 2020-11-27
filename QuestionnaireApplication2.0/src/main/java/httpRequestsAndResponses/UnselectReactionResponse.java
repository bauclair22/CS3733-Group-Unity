package httpRequestsAndResponses;

public class UnselectReactionResponse {
	public final String username;
	public final int statusCode;
	public final String error;
	
	public UnselectReactionResponse (String username, int statusCode) {
		this.username = username;
		//Might also need alternative similar to the request
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public UnselectReactionResponse (String username, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.username = username;
	}
	
	public String toString() {
		if (statusCode == 200) {  // too cute?
			return "UnselectResponse(" + username + ")";
		} else {
			return "ErrorResult(" + username + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
