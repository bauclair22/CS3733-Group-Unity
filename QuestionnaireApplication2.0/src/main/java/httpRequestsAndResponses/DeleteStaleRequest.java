package httpRequestsAndResponses;

import java.util.Arrays;

public class DeleteStaleRequest {
	public float daysOld;
	
	public float getDaysOld() {return daysOld;}
	public void setDaysOld(float daysOld) {this.daysOld = daysOld;}
	
	
	
	public DeleteStaleRequest(float daysOld) {
		this.daysOld=daysOld;
	}
	public DeleteStaleRequest() {
	}

	@Override
	public String toString() {
		return "DeleteStaleRequest(" + daysOld + ")";
	}
	
}
