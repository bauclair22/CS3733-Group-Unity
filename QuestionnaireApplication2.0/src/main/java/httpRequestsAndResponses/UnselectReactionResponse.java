package httpRequestsAndResponses;

public class UnselectReactionResponse {
	public final String memberID;
	public final int statusCode;
	public final String error;
	
	public UnselectReactionResponse (String username, int statusCode) {
		this.memberID = username;
		//Might also need alternative similar to the request
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public UnselectReactionResponse (String username, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.memberID = username;
	}
	
	public String toString() {
		if (statusCode == 200) {  // too cute?
			return "UnselectResponse(" + memberID + ")";
		} else {
			return "ErrorResult(" + memberID + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
