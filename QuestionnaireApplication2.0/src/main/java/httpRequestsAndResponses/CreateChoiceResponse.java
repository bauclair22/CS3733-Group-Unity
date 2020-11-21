package httpRequestsAndResponses;

public class CreateChoiceResponse {
	public String result;
	public int statusCode; //HTTP status code
	public String error;
	
	public CreateChoiceResponse (double value, int statusCode) {
		this.result = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CreateChoiceResponse (int statusCode, String errorMessage) {
		this.result = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode == 200) {
			return "Result(" + result + ")";
		}
		else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
