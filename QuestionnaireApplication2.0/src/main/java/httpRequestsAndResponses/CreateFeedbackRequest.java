package httpRequestsAndResponses;

public class CreateFeedbackRequest {
	String memberName;
	String description;
	
	public CreateFeedbackRequest() {
		
	}

	public CreateFeedbackRequest(String memberName, String description) {
		this.memberName = memberName;
		this.description = description;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "CreateFeedbackRequest("+memberName + "," + description + ")";
	}
}
