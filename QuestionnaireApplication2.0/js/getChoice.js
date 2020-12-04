
/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
function handleRefreshChoice(e) {
   var xhr = new XMLHttpRequest();
   xhr.open("POST", create_member_url, true);
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
  
  
  console.log(choiceJson);
  
  var choiceDisplay = document.getElementById('selectedChoice');
  
  var output = "";
 
	var choiceTitle = choiceJson["description"];
	var choiceMembers = choiceJson["numMembers"];
	var alternatives = choiceJson["alternatives"];
	
	if (status == 200) {
		output = output +
		"<div id=\"selectedChoice\">" +  
		"<form name=\"reactionForm\" method=\"get\">" + 
		"<h2>" + choiceTitle + "</h2>";
		
		for(i = 0; i < alternatives.length; i++){
			if(alternatives[i] !=  null){
				output = output + 
				"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"\">" +
				"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"\">" +
				"<input type=\"button\" id= \"alt1_view\" name= \"alt1_view\" value=" + alternatives[i]["titles"] + " onClick=\"\"><br><br>";
			}
		}
		
	 	output = output +
	 	"</form>" +
	 	"</div>";
		
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
		
	}
  // Update computation result
  choiceDisplay.innerHTML = output;
}