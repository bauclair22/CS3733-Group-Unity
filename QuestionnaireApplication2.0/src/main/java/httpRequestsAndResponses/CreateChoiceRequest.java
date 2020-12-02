package httpRequestsAndResponses;

import java.util.Arrays;

public class CreateChoiceRequest {
	public String description;
	public int numMembers;
	public String alternativeTitles[];
	
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public int getNumMembers() {return numMembers;}
	public void setNumMembers(int numMembers) {this.numMembers = numMembers;}
	
	public String getAlternativeTitle(int i) {return alternativeTitles[i];}
	public void setAlternativeTitles(String[] alternativeTitles) {this.alternativeTitles = alternativeTitles;}
	
	
	public CreateChoiceRequest( String description, int numMembers, String[] alternativeTitles) {
		this.description = description;
		this.numMembers = numMembers;
		this.alternativeTitles = alternativeTitles;
	}
	public CreateChoiceRequest() {
	}

	@Override
	public String toString() {
		return "CreateChoiceRequest(" + description + "," + numMembers
				+ ",=" + Arrays.toString(alternativeTitles) + ")";
	}
	
	
	
}
