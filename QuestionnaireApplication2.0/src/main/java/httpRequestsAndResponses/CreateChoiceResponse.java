package httpRequestsAndResponses;

public class CreateChoiceResponse {
	public String response;
	public final int httpCode;
	
	public CreateChoiceResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public CreateChoiceResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		if(httpCode == 200) {
		return "Response choice ID(" + response + ")";
		}
		else {
			return "ErrorResult(" + httpCode + ", err=" + response + ")";
		}
	}
}
