


function formatHeader(choiceTitle, currentUsers, max){
	return "<h2>(" + currentUsers + "/" + max +")" + choiceTitle + "</h2>";
}

function formatAlternates(altNum, altTitle){
	var agree = "alt" + altNum + "_agree";
	var diagree = "alt" + altNum + "_agree";
	var view = "alt" + altNum + "_agree";
	var output = "";
	output = output + "<input type=\"button\" id= " + agree + " name= "+ agree+" value=\"^\"  onClick=""> <input type=\"button\" id= " + disagree + " name= "+ disagree+" value=\"v\"  onClick="">  <input type=\"button\" id= "view" name= "view" value="altTitle"  onClick=""><br><br>";
	return output;
}


/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
function refreshChoicesList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", choice_list_url, true);
   xhr.send();
   
   console.log("sent request to get choices");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processResponse(xhr.responseText);
    } else {
      processResponse("N/A");
    }
  };
}

/**
 * Loads the choice that the user signs into
 */
function processResponse(result) {
  console.log("result: " + result);
  //var choiceJson = JSON.parse(result);
  
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  // example of choice parsed
    
  var choiceJson = {		
  		"id" : "ChoiceID"
  		"description":"New Choice",
  		"numMembers":"6",
		"alternativeTitles":["Alt1","ALt2"],
		"alternatives":["Alt2 D","Alt2 D"]
	};	
  
  concole.log(choiceJson);
  
  var choiceDisplay = document.getElementById('selectedChoice');
  
  var output = "";
 
	var choiceTitle = choiceJson["description"];
	var	choiceDescription = choiceJson["description"];
	var choiceMembers = choiceJson["numMembers"];
	var alternatives = choiecJson["alternativeTitles"];
	
	if (true) {
		output = output + "<div id=\"choice" +  + "\">" + choiceTitle + "<br><br>" + choiceMembers + "<br><br>" + choiceDescription + "<br></div>";
		
		<div id="selectedChoice" > 
		<form name="reactionForm" method="get">
		
		/*
		<h2>(5/7) Choice</h2>
		<input type="button" id= "alt1_agree" name= "alt1_agree" value="^"  onClick="">
	  	<input type="button" id= "alt1_disagree" name= "alt1_disagree" value="v"  onClick="">
		<input type="button" id= "alt1_view" name= "alt1_view" value="alt1"  onClick=""><br><br>
		*/
		
	 	 <!-- <input type="button" value="Save Changes"  onClick=""> -->
	 	 </form>
	 	</div>
		
	}
 

  // Update computation result
  choiceDisplay.innerHTML = output;
}