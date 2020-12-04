package httpRequestsAndResponses;

import java.util.ArrayList;
import java.util.List;

import model.Choice;

public class RefreshChoiceResponse {
	public final Choice choice;
	public final int statusCode;
	public final String error;
	
	public RefreshChoiceResponse (Choice choice, int code) {
		this.choice = choice;
		this.statusCode = code;
		this.error = "";
	}
	
	public RefreshChoiceResponse (int code, String errorMessage) {
		this.choice = null;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (choice == null) { return "Error: " + error; }
		return choice.toString();
	}

}
