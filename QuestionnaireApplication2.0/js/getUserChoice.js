

/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
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

/**
 * 
 */
function handleRefreshChoiceClick(e) {
		var data = {};
		
		var output = output +
		"<div id=\"selectedChoice\">" + 
		"<p>Loading Choice...</p>" +
		"</div>";
		document.getElementById('selectedChoice').innerHTML = output;
		
		    	
		data["choiceID"] = document.getElementById("choiceID_new").innerHTML;
		data["memberID"] = document.getElementById("memberID").innerHTML;
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
  
	var choiceDisplay = document.getElementById('selectedChoice');
	// getting the response and the http code
	var js = JSON.parse(result);
	console.log(js);
  
	// getting the JSON object
  	var status = js["httpCode"];
  	var choice = js["choice"];

  	
	var choiceTitle = choice["description"];
	var choiceMembers = choice["numMembers"];
	var alternatives = choice["alternatives"];  //array list
	
	 var output = "";
	
	//creates an array of the ALt ids to process for later
	 var choiceID = choice["ID"];
	 var memberID = js["memberID"];
	 var altIDList = [];
	  for(i = 0; i < alternatives.length; i++){
		  if(alternatives[i] != null){
			  altIDList.push(alternatives[i]["altID"]);
		  }
	  }
	  
	  
	 
	
	if (status == 200) {
		//Storing the member ID  and alt Somewhere
		
		
		document.getElementById("memberID").innerHTML = memberID;
		document.getElementById("altID").innerHTML = altIDList.toString();
		document.getElementById("choiceID_new").innerHTML = choiceID;
		
		document.getElementById("memberID_meta").content = memberID;
		document.getElementById("altID_meta").content = altIDList.toString();
		document.getElementById("choiceID_meta").content = choiceID;
		console.log("verify that metadata has changed");
		{
			if(document.getElementById("memberID_meta").content != "0"){
				console.log("memberID_meta success");
			}
			if(document.getElementById("altID_meta").content != "0"){
				console.log("altID_meta success");
			}
			if(document.getElementById("choiceID_meta").content != "0"){
				console.log("choiceID_meta success");
			}
		}

		
		//perform normal operation 
		
		output = output +
		"<div id=\"selectedChoice\">" +  
		"<form name=\"reactionForm\" method=\"get\">" + 
		"<h2>" + choiceTitle + "</h2>" +
		"<input type= \"button\" value= \"Refresh Choice\"  onClick=\"handleRefreshChoiceClick(this)\"><br><br>";
		
		for(i = 0; i < alternatives.length; i++){
			//if an alt is not null
			if(alternatives[i] != null){
				
			//handles creating the headers for each choice	
			output = output + 
			"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"handleApproverAltClick(this," + i + ")\">" +
			"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"handleDisapproverAltClick(this," + i + ")\">" +
			"<label>" + alternatives[i]["title"] + "</label>";
			
			
			//if we are able to see alternatives when the choice is give, return that
			var approvers = alternatives[i]["approvers"];
			var disapprovers = alternatives[i]["disapprovers"];
			
			output = output + 
			"<p># of Approvers: " + approvers.length + "</p>" +
			  "<p class= \"a\">";
			  
			  for(j = 0; j < approvers.length; j++){
				  if(approvers[j] != null){
					output = output + approvers[j] + "<br>";
					}
			  }
			  output = output +
			  "</p>" +	
			  "<p># of Disapprovers: " + disapprovers.length + "</p>" + 
			  "<p class= \"a\">";
			  for(k = 0; k < disapprovers.length; k++){
				  if(disapprovers[k] != null){
					output = output +disapprovers[k] + "<br>";
					}
			  }
			  output + output +
			  "</p><br>" ;
			
			}
		}
	 	output = output +
	 	"</form>" +
	 	"</div>";
	}
	else {
		output = output +
		"<div id=\"selectedChoice\">" + 
		"<p>Sorry, there seems to be an error with your login</p>" +
		"</div>";
	}
		

  // Update computation result
  choiceDisplay.innerHTML = output;
}