

/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
/*
function handleRefreshChoice(e) {
	
   var xhr = new XMLHttpRequest();
   xhr.open("POST", create_member_url, true);  //makes sure to call the lamda function
   xhr.send();
   
   console.log("Getting the choice o the user");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processRefreshChoice(xhr.responseText);
    } else {
    	processRefreshChoice("N/A");
    }
  };
}
*/

/**
 * 
 */
function handleRefreshChoiceClick(e) {
		var data = {};
		
		/*
		var output = output +
		"<div id=\"selectedChoice\">" + 
		"<p>Loading Choice...</p>" +
		"</div>";
		document.getElementById('selectedChoice').innerHTML = output;
		*/
		   	
		data["choiceID"] = getChoiceID();
		data["memberID"] = getMemberID();
		
		var js = JSON.stringify(data);
	
	   var xhr = new XMLHttpRequest();
	   xhr.open("POST", updateChoice_url , true);  //makes sure to call the lamda function
	   xhr.send(js);
	   
	   console.log("Refreshing Choice");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	      console.log ("XHR:" + xhr.responseText);
	      processRefreshChoice(xhr.responseText);
	    } else {
	    	processRefreshChoice("N/A");
	    }
	  };
	}

/**
 * Loads the choice that the user signs into
 */
function processRefreshChoice(result) {
	console.log("result: " + result);
  
	// getting the response and the http code
	var js = JSON.parse(result);
	console.log(js);
  
	// getting the JSON object
  	var status = js["httpCode"];
  	var choice = js["choice"];
  	
	if (status == 200) {
		displayUncompletedChoice(choice);
	}
}


function openNewChoice(result){
	console.log("result: " + result);
	  
	// getting the response and the http code
	var js = JSON.parse(result);
	console.log(js);
  
	// getting the JSON object
  	var status = js["httpCode"];
  	var choice = js["choice"];

	var choiceTitle = choice["description"];
	var choiceMembers = choice["numMembers"];
	var alternatives = choice["alternatives"];  //array list
	
	
	//creates an array of the ALt ids to process for later
	 
	 var choiceID = choice["ID"];
	 var memberID = js["memberID"];
	 var altIDList = [];
	 for(i = 0; i < alternatives.length; i++){
		  if(alternatives[i] != null){
			  altIDList.push(alternatives[i]["altID"]);
		  }
	  }
	  setMemberID(memberID);
	  setAltID(altIDList.toString());
	  setChoiceID(choiceID);
	  

	if (status == 200) {
		displayUncompletedChoice(choice);
	}
}

/**
 * Displays The Choice
 * @param choice
 * @returns
 */
function displayUncompletedChoice(choice){
	
	var choiceTitle = choice["description"];
	var alternatives = choice["alternatives"];  //array list
	
	//perform normal operation 
	var choiceDisplay = document.getElementById('selectedChoice');
	var output = "";
	
	output = output +
	"<div id=\"selectedChoice\">" +  
	"<form name=\"reactionForm\" method=\"get\">" + 
	"<input type= \"button\" value= \"&#128260;\"  onClick=\"handleRefreshChoiceClick(this)\"> +" +
	"<div id=\"addFeedbackDiv\" name=\"addfeedbackDiv\"></div>" + 
	"<h2>" + choiceTitle + "</h2>";
	
	for(i = 0; i < alternatives.length; i++){
		//if an alt is not null
		if(alternatives[i] != null){
			
		//handles creating the headers for each choice	
		output = output + 
		//approver
		//disapprover
		//feedback
		//formward feedback
		"<input class=\"alt\" type=\"button\" value=\"&#9989;\"  onClick=\"handleApproverAltClick(this," + i + ")\">" +
		"<input class=\"alt\" type=\"button\" value=\"&#10062;\"  onClick=\"handleDisapproverAltClick(this," + i + ")\">" +
		"<input class=\"alt\" type=\"button\" value=\"&#128172;\"  onClick=\"openAddFeedbackForm(this," +  i + ")\">" + 
		"<input class=\"alt\" type=\"button\" value=\"&#9193;\"  onClick=\"\">" + 
		"<label>" + alternatives[i]["title"] + "</label>";
		
		
		//if we are able to see alternatives when the choice is give, return that
		var approvers = alternatives[i]["approvers"];
		var disapprovers = alternatives[i]["disapprovers"];
		
		output = output + 
		"<p>Approvers: " + approvers.length + "<br>";
		  
		  for(j = 0; j < approvers.length; j++){
			  if(approvers[j] != null){
				output = output + approvers[j] + "<br>";
				}
		  }
		  output = output +
		  "</p>" +	
		  "<p>Disapprovers: " + disapprovers.length + "<br>";
		  for(k = 0; k < disapprovers.length; k++){
			  if(disapprovers[k] != null){
				output = output +disapprovers[k] + "<br>";
				}
		  }
		  output + output +
		  "</p><br>" ;
		  console.log("alt: " + i);
		  console.log("Length of feedback for alt is: " + alternatives[i]["feedback"].length);
		
		}
	}
 	output = output +
 	"</form>" +
 	"</div>";
 	
 	choiceDisplay.innerHTML = output;
}



