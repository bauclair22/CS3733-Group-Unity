package httpRequestsAndResponses;

public class UnselectReactionRequest {
	String memberID;
	String altid;

	public String getAltid() {return altid;}
	public void setAltid(String altid) {this.altid = altid;}

	public String getmemberID() {return memberID;}
	public void setmemberID(String username) {this.memberID = username;}
	
	public UnselectReactionRequest(String s, String altid) {
		this.memberID = s;
		this.altid = altid;
	}
	
	public UnselectReactionRequest() {
	}
	
	public String toString() {
		return memberID + " unselects their reaction to this Alternative";
	}
}
