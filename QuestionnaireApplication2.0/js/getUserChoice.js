

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
  
  // getting the response and the http code
  var js = JSON.parse(result);
  console.log(js);
  
  // getting the JSON object
  var status = js["httpCode"];
  var choiceJson = js["choice"];
  var memberID = js["memberID"];
  
  
 
  console.log(altIDList);
  
  console.log(status);
  console.log(choiceJson);
  //console.log("choice parsed:" + choiceJson);
  
  var choiceDisplay = document.getElementById('selectedChoice');
  
  var output = "";
 
	var choiceTitle = choiceJson["description"];
	var choiceMembers = choiceJson["numMembers"];
	var alternatives = choiceJson["alternatives"];  //array list
	
	
	//creates an array of the ALt ids to process for later
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
		
		document.getElementById("memberID_meta").content = memberID;
		document.getElementById("altID_meta").content = altIDList.toString();
		{
			if(document.getElementById("memberID_meta").content != "0"){
				console.log("memberID_meta success");
			}
			if(document.getElementById("altID_meta").content != "0"){
				console.log("altID_meta success");
			}
		}

		
		//perform normal operation 
		
		output = output +
		"<div id=\"selectedChoice\">" +  
		"<form name=\"reactionForm\" method=\"get\">" + 
		"<h2>" + choiceTitle + "</h2>" +
		"<input type= \"button\" value= \"Refresh Choice\"  onClick=\"handleRefreshChoiceClick(this)\"><br>";
		
		for(i = 0; i < alternatives.length; i++){
			//if an alt is not null
			if(alternatives[i] != null){
				
			//handles creating the headers for each choice	
			output = output + 
			"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"handleApproverAltClick(this," + i + ")\">" +
			"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"handleDisapproverAltClick(this," + i + ")\">" +
			"<label>" + alternatives[i]["title"] + "</label>" +
			"<input type=\"button\" id= \"alt1_view\" name= \"alt1_view\" value=" + alternatives[i]["title"] + " onClick=\"\"><br><br>";
			
			
			//if we are able to see alternatives when the choice is give, return that
			
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