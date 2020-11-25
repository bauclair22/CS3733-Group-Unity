package httpRequestsAndResponses;

public class ParticipateChoiceRequest {
	String username;
	String password;
	String id;
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String toString() {
		return "ParticipateChoiceRequest(" + username + ", for choice " + id + ")";
	}
	
	public ParticipateChoiceRequest(String username, String password, String id) {
		this.id = id;
		this.password = password;
		this.username = username;
	}
	
	//Separate constructor for if the user chooses not to include a password
	public ParticipateChoiceRequest(String username, String id) {
		this.id = id;
		this.username = username;
	}
	
	public ParticipateChoiceRequest() {
	}
}
