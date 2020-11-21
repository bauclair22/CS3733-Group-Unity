package httpRequestsAndResponses;

import java.util.Arrays;

public class CreateChoiceRequest {
	public String title;
	public String description;
	public int numMembers;
	public String alternatives[];
	public String alternativeTitles[];
	public boolean system;
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public int getNumMembers() {return numMembers;}
	public void setNumMembers(int numMembers) {this.numMembers = numMembers;}
	
	public String[] getAlternatives() {return alternatives;}
	public void setAlternatives(String[] alternatives) {this.alternatives = alternatives;}
	
	public String[] getAlternativeTitles() {return alternativeTitles;}
	public void setAlternativeTitles(String[] alternativeTitles) {this.alternativeTitles = alternativeTitles;}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public CreateChoiceRequest(String title, String description, int numMembers, String[] alternatives,
			String[] alternativeTitles) {
		this.title = title;
		this.description = description;
		this.numMembers = numMembers;
		this.alternatives = alternatives;
		this.alternativeTitles = alternativeTitles;
	}
	public CreateChoiceRequest() {
	}
	public CreateChoiceRequest(String title, String description, int numMembers, String[] alternatives,
			String[] alternativeTitles, boolean system) {
		this.title = title;
		this.description = description;
		this.numMembers = numMembers;
		this.alternatives = alternatives;
		this.alternativeTitles = alternativeTitles;
		this.system = system;
	}
	@Override
	public String toString() {
		return "CreateChoiceRequest(" + title + "," + description + "," + numMembers
				+ ",=" + Arrays.toString(alternatives) + ","
				+ Arrays.toString(alternativeTitles) + ")";
	}
	
	
	
}
