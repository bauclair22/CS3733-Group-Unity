package httpRequestsAndResponses;

public class SelectDisapprovalRequest {
	String memberID;
	String altid;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setUsername(String username) {this.memberID = username;}
	
	public SelectDisapprovalRequest(String s, String altid) {
		this.memberID = s;
		this.altid = altid;
	}
	
	public SelectDisapprovalRequest() {
	}
	
	public String toString() {
		return memberID + " disapproves this Alternative";
	}
}
