package httpRequestsAndResponses;

public class SelectApprovalResponse {
	public final String memberID;
	public final int statusCode;
	public final String error;
	
	public SelectApprovalResponse (String memberID, int statusCode) {
		this.memberID = memberID;
		//Might also need alternative similar to the request
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public SelectApprovalResponse (String memberID, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.memberID = memberID;
	}
	
	public String toString() {
		if (statusCode == 200) {  // too cute?
			return "ApprovalResponse(" + memberID + ")";
		} else {
			return "ErrorResult(" + memberID + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
