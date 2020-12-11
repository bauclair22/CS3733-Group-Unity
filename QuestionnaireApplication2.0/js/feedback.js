

function cancelAddFeedbackForm(e){
	console.log("User Requested To Close Feedback");
	var div = document.getElementById("addFeedbackDiv");
	
	setFeedbackNum(null);
	var output = "";
	output = output + "<div id=\"addFeedbackDiv\"></div>"
	
	div.innerHTML = output;
	
}

function openAddFeedbackForm(e, feedbackNum){
	var div = document.getElementById("addFeedbackDiv");
	//var form = document.addFeedbackForm;
	//open feedback
	//you know alt your adjusting
	//possible save information in the mata data
	setFeedbackNum(feedbackNum);
	var output = "";
	
	output = output +
	"<div id=\"addFeedbackDiv\">" +
	"<form name=\"addFeedbackForm\" id=\"addFeedbackForm\">" +
	"<label>Add Your Feedback</label><br>" +
	"<textarea name=\"descriptionFeedback\" id=\"descriptionFeedback\" rows=\"4\" cols=\"30\"></textarea><br>" +
	"<input type=\"button\" value=\"Add\" onClick=\"handleAddFeedbackClick(this," + feedbackNum + ")\"></input>" +
	"<input type=\"button\" value=\"Cancel\" onClick=\"cancelAddFeedbackForm(this)\"></input>" +
	"</form>" +
	"</div>";
	
	div.innerHTML = output;
}



function handleAddFeedbackClick(e, feedbackNum){	
	//var div = document.getElementById("addFeedbackDiv");
	//var form = div.getElementById("addFeedbackForm");	
	var data = {};
	//var feedbackNum = getFeedbackNum();
	
	
	data["choiceID"] = getChoiceID();
	data["memberID"] = getMemberID();
	data["altid"] = getAltID(feedbackNum);
	data["description"] = document.getElementById("descriptionFeedback").value;   //again the message that they wanna get
	
		
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	   xhr.open("POST", addFeedback_url, true);  //makes sure to call the lamda function
	   xhr.send(js);
	   
	   console.log("Attemp to Add Feedback to: " + getAltID(feedbackNum));

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	      console.log ("XHR:" + xhr.responseText);
	      processRefreshChoice(xhr.responseText);
	    } else {
	    	processRefreshChoice("N/A");
	    	console.log("issue with handleAddFeedback");
	    }
	  };
}