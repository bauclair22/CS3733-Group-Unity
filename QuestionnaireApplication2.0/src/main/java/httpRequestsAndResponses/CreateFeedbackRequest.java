package httpRequestsAndResponses;

public class CreateFeedbackRequest {
	String memberID;
	String altid;
	String description;
	
	public CreateFeedbackRequest() {
		
	}

	public CreateFeedbackRequest(String memberID, String altid, String description) {
		this.memberID = memberID;
		this.altid = altid;
		this.description = description;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "CreateFeedbackRequest("+memberID + "," + altid + "," + description + ")";
	}

	public String getAltid() {
		return altid;
	}

	public void setAltid(String altid) {
		this.altid = altid;
	}
}
