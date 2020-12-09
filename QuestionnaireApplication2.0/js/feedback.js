

function cancelAddFeedbackForm(e){
	console.log("User Requested To Cancel Feedback");
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
	"<textarea name=\"description\" rows=\"4\" cols=\"30\"></textarea><br>" +
	"<input type=\"button\" value=\"Add\" onClick=\"handleAddFeedbackClick(this)\"></input>" +
	"<input type=\"button\" value=\"Cancel\" onClick=\"cancelAddFeedbackForm(this)\"></input>" +
	"</form>" +
	"</div>";
	
	div.innerHTML = output;
}



function handleAddFeedbackClick(e){
	console.log("attempting to add feedback for: " + feedbackNum);
	var form = document.addFeedbackForm;
	var data = {};
	
	data["memberID"] = getMemberID();
	data["altid"] = getAltID(feedbackNum);
	data["description"] = form.description.value;   //again the message that they wanna get
	
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	   xhr.open("POST", addFeedback_url, true);  //makes sure to call the lamda function
	   xhr.send();
	   
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