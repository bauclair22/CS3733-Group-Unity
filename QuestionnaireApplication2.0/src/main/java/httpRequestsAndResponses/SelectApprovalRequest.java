package httpRequestsAndResponses;

public class SelectApprovalRequest {
	String memberID;
	String altid;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setmemberID(String username) {this.memberID = username;}
	
	public SelectApprovalRequest(String s, String altid) {
		this.memberID = s;
		this.altid = altid;
	}
	
	public SelectApprovalRequest() {
	}
	
	public String toString() {
		return memberID + " approves this Alternative";
	}
}
