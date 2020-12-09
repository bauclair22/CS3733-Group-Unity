package httpRequestsAndResponses;

public class SelectDisapprovalRequest {
	String memberID;
	String altid;
	String choiceID;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setmemberID(String memberID) {this.memberID = memberID;}
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String choiceID) {this.choiceID = choiceID;}
	
	public SelectDisapprovalRequest(String s, String altid, String choiceID) {
		this.memberID = s;
		this.altid = altid;
		this.choiceID = choiceID;
	}
	
	public SelectDisapprovalRequest() {
	}
	
	public String toString() {
		return memberID + " disapproves this Alternative";
	}
}
