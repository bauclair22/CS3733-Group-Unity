package httpRequestsAndResponses;

public class SelectApprovalRequest {
	String username;
	int altid;

	public int getAltid() {return altid;}
	public void setAltid(int altid) {this.altid = altid;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public SelectApprovalRequest(String s, int altid) {
		this.username = s;
		this.altid = altid;
	}
	
	public SelectApprovalRequest() {
	}
	
	public String toString() {
		return username + " approves this Alternative";
	}
}
