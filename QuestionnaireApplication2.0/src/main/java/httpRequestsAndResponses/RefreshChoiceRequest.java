package httpRequestsAndResponses;

public class RefreshChoiceRequest {
	String choiceID;
	String memberID;
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String choiceID) {this.choiceID = choiceID;}

	public String getMemberID() {return memberID;}
	public void setMemberID(String memberID) {this.memberID = memberID;}
	
	public String toString() {
		return "RefreshChoiceRequest(" + memberID + ", for choice " + choiceID + ")";
	}

	public RefreshChoiceRequest(String choiceID, String memberID) {
		this.choiceID = choiceID;
		this.memberID = memberID;
	}
	
	public RefreshChoiceRequest() {
	}
}
