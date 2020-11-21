package httpRequestsAndResponses;

public class CreateChoiceRequest {
	String title;
	String description;
	int numMembers;
	String alternatives[];
	String alternativeTitles[];
	
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
	
}
