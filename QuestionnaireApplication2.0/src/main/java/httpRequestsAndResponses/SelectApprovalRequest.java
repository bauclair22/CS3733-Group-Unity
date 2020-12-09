package httpRequestsAndResponses;

public class SelectApprovalRequest {
	String memberID;
	String altid;
	String choiceID;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setmemberID(String username) {this.memberID = username;}
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String choiceID) {this.choiceID = choiceID;}
	
	public SelectApprovalRequest(String s, String altid, String choiceID) {
		this.memberID = s;
		this.altid = altid;
		this.choiceID = choiceID;
	}
	
	public SelectApprovalRequest() {
	}
	
	public String toString() {
		return memberID + " approves this Alternative";
	}
}
