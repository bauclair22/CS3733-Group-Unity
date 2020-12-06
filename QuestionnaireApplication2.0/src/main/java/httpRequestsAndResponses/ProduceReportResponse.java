package httpRequestsAndResponses;

import java.util.ArrayList;
import java.util.List;

import model.Choice;
import model.ChoiceReport;


public class ProduceReportResponse {
	public final List<ChoiceReport> choiceReport;
	public final int statusCode;
	public final String error;
	
	public ProduceReportResponse (List<ChoiceReport>  choiceReport, int code) {
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
