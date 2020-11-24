package httpRequestsAndResponses;

public class SelectDisapprovalRequest {
	String username;
	int altid;

	public int getAltid() {return altid;}
	public void setAltid(int altid) {this.altid = altid;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public SelectDisapprovalRequest(String s, int altid) {
		this.username = s;
		this.altid = altid;
	}
	
	public SelectDisapprovalRequest() {
	}
	
	public String toString() {
		return username + " disapproves this Alternative";
	}
}
