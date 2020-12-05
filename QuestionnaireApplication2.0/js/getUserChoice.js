

function formatHeader(choiceTitle, currentUsers, max){
	/*
	"httpCode": 200,
	"response": "everything went through",
	"memberId";
	"choice": 
	{
	  "description": "why  am i here just to suffer",
	  "alternatives": [
	    {
	      "title": "dwhlawlidjaw",
	      "approvers": [],
	      "disapprovers": [],
	      "feedback": []
	    },
	    {
	      "title": "a3wrdae2weaw",
	      "approvers": [],
	      "disapprovers": [],
	      "feedback": []
	    },
	    {
	      "title": "dwghukcaw",
	      "approvers": [],
	      "disapprovers": [],
	      "feedback": []
	    },
	    null,
	    null
	  ],
	  "numMembers": 17,
	  "isCompleted": false,
	  "ID": 1
	}
	}
	*/
}



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
 * Loads the choice that the user signs into
 */
function processRefreshChoice(result) {
  console.log("result: " + result);
  
  // getting the response and the http code
  var js = JSON.parse(result);
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
	
	console.log(choiceTitle);
	console.log(choiceMembers);
	console.log(alternatives);
	console.log(altIDList[0]);
	console.log(alternatives[0]["altID"]);
	
	if (status == 200) {
		//Storing the member ID  and alt Somewhere
		document.getElementById("memberID").innerHTML = memberID;
		//document.getElementById("altID").innterHTML = altIDList[0];
		//document.getElementById("altID").innterHTML = alternatives[0]["altID"];
		//document.getElementById("altID").innterHTML = "Look I am new";

		
		//perform normal operation 
		
		output = output +
		"<div id=\"selectedChoice\">" +  
		"<form name=\"reactionForm\" method=\"get\">" + 
		"<h2>" + choiceTitle + "</h2>";
		
		for(i = 0; i < alternatives.length; i++){
			//if an alt is not null
			if(alternatives[i] != null){
			output = output + 
			"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"handleUpdatingAltClick(e)\">" +
			"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"\">" +
			"<input type=\"button\" id= \"alt1_view\" name= \"alt1_view\" value=" + alternatives[i]["title"] + " onClick=\"\"><br><br>";
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
		
		/*
		<div id="selectedChoice" > 
		<form name="reactionForm" method="get">

		<h2>(5/7) Choice</h2>
		<input type="button" id= "alt1_agree" name= "alt1_agree" value="^"  onClick="">
	  	<input type="button" id= "alt1_disagree" name= "alt1_disagree" value="v"  onClick="">
		<input type="button" id= "alt1_view" name= "alt1_view" value="alt1"  onClick=""><br><br>
		
	 	 <!-- <input type="button" value="Save Changes"  onClick=""> -->
	 	 </form>
	 	</div>
	 	*/
  // Update computation result
  choiceDisplay.innerHTML = output;
}