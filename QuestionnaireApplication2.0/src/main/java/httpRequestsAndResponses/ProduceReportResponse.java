package httpRequestsAndResponses;

import java.util.ArrayList;
import java.util.List;

import model.Choice;


public class ProduceReportResponse {
	public final List<Choice> list;
	public final int statusCode;
	public final String error;
	
	public ProduceReportResponse (List<Choice> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ProduceReportResponse (int code, String errorMessage) {
		this.list = new ArrayList<Choice>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyChoices"; }
		return "AllChoices(" + list.size() + ")";
	}
}
