package httpRequestsAndResponses;

import java.util.Arrays;

public class CreateChoiceRequest {
	public String description;
	public int numMembers;
	public String alternatives[];
	public String alternativeTitles[];
	
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public int getNumMembers() {return numMembers;}
	public void setNumMembers(int numMembers) {this.numMembers = numMembers;}
	
	public String[] getAlternatives() {return alternatives;}
	public void setAlternatives(String[] alternatives) {this.alternatives = alternatives;}
	
	public String[] getAlternativeTitles() {return alternativeTitles;}
	public void setAlternativeTitles(String[] alternativeTitles) {this.alternativeTitles = alternativeTitles;}
	
	
	public CreateChoiceRequest( String description, int numMembers, String[] alternatives,
			String[] alternativeTitles) {
		this.description = description;
		this.numMembers = numMembers;
		this.alternatives = alternatives;
		this.alternativeTitles = alternativeTitles;
	}
	public CreateChoiceRequest() {
	}

	@Override
	public String toString() {
		return "CreateChoiceRequest(" + description + "," + numMembers
				+ ",=" + Arrays.toString(alternatives) + ","
				+ Arrays.toString(alternativeTitles) + ")";
	}
	
	
	
}
