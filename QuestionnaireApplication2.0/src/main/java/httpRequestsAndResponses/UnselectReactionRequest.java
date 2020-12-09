package httpRequestsAndResponses;

public class UnselectReactionRequest {
	String memberID;
	String altid;
	String choiceID;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setmemberID(String username) {this.memberID = username;}
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String choiceID) {this.choiceID = choiceID;}
	
	public UnselectReactionRequest(String s, String altid, String choiceID) {
		this.memberID = s;
		this.altid = altid;
		this.choiceID = choiceID;
	}
	
	public UnselectReactionRequest() {
	}
	
	public String toString() {
		return memberID + " unselects their reaction to this Alternative";
	}
}
