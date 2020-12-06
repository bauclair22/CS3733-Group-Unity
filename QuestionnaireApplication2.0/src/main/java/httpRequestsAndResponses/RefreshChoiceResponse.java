package httpRequestsAndResponses;

import java.util.ArrayList;
import java.util.List;

import model.Choice;

public class RefreshChoiceResponse {
	public final Choice choice;
	public final int httpCode;
	public final String error;
	
	public RefreshChoiceResponse (Choice choice, int code) {
		this.choice = choice;
		this.httpCode = code;
		this.error = "";
	}
	
	public RefreshChoiceResponse (int code, String errorMessage) {
		this.choice = null;
		this.httpCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (choice == null) { return "Error: " + error; }
		return choice.toString();
	}

}
