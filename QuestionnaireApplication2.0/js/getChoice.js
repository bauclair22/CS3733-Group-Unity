

function formatHeader(choiceTitle, currentUsers, max){

	"Choice [" +
	"description=why  am i here just to suffer, " +
	"alternatives=[model.Alternative@7722c3c3, model.Alternative@2ef3eef9, model.Alternative@243c4f91, null, null], " +
	"numMembers=17, " +
	"teamMembers=[], " +
	"idNumber=0, " +
	"isCompleted=false, " +
	"dateCompleted=null" +
	"]"
}

function formatAlt(altTitle){
	//var agree = "alt" + altNum + "_agree";
	//var diagree = "alt" + altNum + "_agree";
	//var view = "alt" + altNum + "_agree";
	//console.log("formating alternatives");
	//console.log(agree);
	//console.log(disagree);
	//console.log(view);
	
	var output = "";
	output = output + 
	"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"\">" +
	"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"\">" +
	"<input type=\"button\" id= \"alt1_view\" name= \"alt1_view\" value=" + altTitle + " onClick=\"\"><br><br>";
	return output;
}


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
  var choiceJson = JSON.parse(js["response"]);
  
  console.log(choiceJson);
  
  var choiceDisplay = document.getElementById('selectedChoice');
  
  var output = "";
 
	var choiceTitle = choiceJson["description"];
	var choiceMembers = choiceJson["numMembers"];
	var alternatives = choiceJson["alternatives"];
	
	if (true) {
		output = output +
		"<div id=\"selectedChoice\">" +  
		"<form name=\"reactionForm\" method=\"get\">" + 
		"<h2>" + choiceTitle + "</h2>";
		
		for(i = 0; i < alternatives.length; i++){
			output = output + 
			"<input type=\"button\" id= \" alt1_agree\" name= \"alt1_agree\" value=\"^\"  onClick=\"\">" +
			"<input type=\"button\" id= \" alt1_disagree\" name= \"alt1_disagree\" value=\"v\"  onClick=\"\">" +
			"<input type=\"button\" id= \"alt1_view\" name= \"alt1_view\" value=" + alternatives[i] + " onClick=\"\"><br><br>";
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