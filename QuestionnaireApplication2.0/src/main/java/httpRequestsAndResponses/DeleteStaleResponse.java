package httpRequestsAndResponses;

import java.util.List;

import model.ChoiceReport;

public class DeleteStaleResponse {
	public final List<ChoiceReport> choiceReport;
	public final int httpCode;
	public final String error;
	
	public DeleteStaleResponse (List<ChoiceReport>  choiceReport, int code) {
		this.choiceReport = choiceReport;
		this.httpCode = code;
		this.error = "";
	}
	
	public DeleteStaleResponse (int code, String errorMessage) {
		this.choiceReport = null;
		this.httpCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (choiceReport == null) { return "EmptyChoices"; }
		return "AllChoices(" + choiceReport + ")";
	}
}

