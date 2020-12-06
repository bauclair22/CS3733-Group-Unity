package httpRequestsAndResponses;

import java.util.ArrayList;
import java.util.List;

import model.Choice;


public class ProduceReportResponse {
	public final List<Choice> choiceReport;
	public final int statusCode;
	public final String error;
	
	public ProduceReportResponse ( List<Choice> choiceReport, int code) {
		this.choiceReport = choiceReport;
		this.statusCode = code;
		this.error = "";
	}
	
	public ProduceReportResponse (int code, String errorMessage) {
		this.choiceReport = null;
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (choiceReport == null) { return "EmptyChoices"; }
		return "AllChoices(" + choiceReport + ")";
	}
}
