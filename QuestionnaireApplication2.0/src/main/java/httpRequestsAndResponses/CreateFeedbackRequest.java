package httpRequestsAndResponses;

public class CreateFeedbackRequest {
	String memberID;
	String altid;
	String description;
	String choiceID;
	
	public CreateFeedbackRequest() {
		
	}

	public CreateFeedbackRequest(String memberID, String altid, String description, String choiceID) {
		this.memberID = memberID;
		this.altid = altid;
		this.description = description;
		this.choiceID = choiceID;
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
		return "CreateFeedbackRequest("+memberID + "," + altid + "," + description + "," + choiceID + ")";
	}

	public String getChoiceID() {
		return choiceID;
	}

	public void setChoiceID(String choiceID) {
		this.choiceID = choiceID;
	}

	public String getAltid() {
		return altid;
	}

	public void setAltid(String altid) {
		this.altid = altid;
	}
}
