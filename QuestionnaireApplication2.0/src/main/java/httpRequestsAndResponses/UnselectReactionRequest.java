package httpRequestsAndResponses;

public class UnselectReactionRequest {
	String username;
	int altid;

	public int getAltid() {return altid;}
	public void setAltid(int altid) {this.altid = altid;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public UnselectReactionRequest(String s, int altid) {
		this.username = s;
		this.altid = altid;
	}
	
	public UnselectReactionRequest() {
	}
	
	public String toString() {
		return username + " unselects their reaction to this Alternative";
	}
}
