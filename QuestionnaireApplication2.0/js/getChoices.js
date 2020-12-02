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
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processResponse(result) {
  console.log("result: " + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  console.log(js.list)
  
  var choiceDisplay = document.getElementById('selectedChoice');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var choiceJson = js.list[i];
    console.log(choiceJson);
    
    
    var choiceTitle = choiceJson["title"];
    var	choiceDescription = choiceJson["description"];
    var choiceMembers = choiceJson["numMembers"];
    if (true) {
    	output = output + "<div id=\"choice" +  + "\">" + choiceTitle + "<br><br>" + choiceMembers + "<br><br>" + choiceDescription + "<br></div>";
    }
  }

  // Update computation result
  choiceDisplay.innerHTML = output;
}