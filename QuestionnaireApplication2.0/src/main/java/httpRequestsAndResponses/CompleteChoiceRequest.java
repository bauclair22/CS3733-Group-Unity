package httpRequestsAndResponses;

public class CompleteChoiceRequest {
	String choiceID;
	String altid;
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String choiceID) {this.choiceID = choiceID;}
	
	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}
	
	public CompleteChoiceRequest(String choiceID, String altid) {
		super();
		this.choiceID = choiceID;
		this.altid = altid;
	}
	
	public CompleteChoiceRequest() {
	}
	
	public String toString() {
		return altid + " is the selected alternative for choice " + choiceID;
	}

}
