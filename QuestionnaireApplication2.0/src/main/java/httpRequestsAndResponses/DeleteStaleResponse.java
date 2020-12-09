package httpRequestsAndResponses;


public class DeleteStaleResponse {
	public String response;
	public final int httpCode;
	
	public DeleteStaleResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public DeleteStaleResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		if(httpCode == 200) {
		return "Delete stale choices(" + response + ")";
		}
		else {
			return "ErrorResult(" + httpCode + ", err=" + response + ")";
		}
	}
}

