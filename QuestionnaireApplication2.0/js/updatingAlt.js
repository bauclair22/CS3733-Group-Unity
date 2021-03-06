
function handleRemoveapprover(js){
	console.log("attempting to remove user from Alt");
	 // console.log(js);
	  xhr = new XMLHttpRequest();
	  
	   xhr.open("POST", unselectApprover_url, true);
	   xhr.send(js);
	   xhr.onloadend = function(){
	   	   if(xhr.readyState == XMLHttpRequest.DONE){
	   		   console.log ("XHR:" + xhr.responseText);
	   		   json = JSON.parse(xhr.responseText);
	   		   //console.log(json);
	   		   var isRemoved_Status = json["httpCode"];
	   		   if(isRemoved_Status == 200){
	       		   console.log("team mate removed");
	       		   processRefreshChoice(xhr.responseText);
	       		   //updateAltDisplay(xhr.responseText);
	       		   console.log("updating display");
	       	   }
	       	   else{
	       		   console.log("unable to process request")
	       		   processRefreshChoice("N/A");
	       	   }
	   	   }
	   	   else{
	   		   console.log("something else happened");
	
	   	   }
	   }
}




/**
 * Adds and removes a approver from the alternative
 * @param e event
 * @param alt, the number that the alt is displayed if it was an array (ex: first alt is 0, sencond is 1...) 
 * @returns
 */
function handleApproverAltClick(e,alt) {
	
	  var data = {};
	  
	  data["memberID"] = getMemberID();
	  data["choiceID"] = getChoiceID();
	  data["altid"] = getAltID(alt);
	 
	  var js = JSON.stringify(data);
	  console.log("JS:" + js);

   var xhr = new XMLHttpRequest();
   xhr.open("POST", selectApprover_url, true);
   xhr.send(js);
   
   console.log("adding user to approver");
   
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      
      var json = JSON.parse(xhr.responseText);
      var isAdded_Status = json["httpCode"];
      if(isAdded_Status == 200){
    	  console.log("team mate added");
    	  console.log("updating display");
    	  processRefreshChoice(xhr.responseText);
      }
      else{
    	  console.log("user already added, attempting to unselect");
    	  // attemps to remove the approver from the alt
    	  handleRemoveapprover(js);
    	  
      }
    }
  }
}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

function handleDisapproverAltClick(e,alt) {
	  var data = {};
	  
	  data["memberID"] = getMemberID();
	  data["choiceID"] = getChoiceID();
	  data["altid"] = getAltID(alt);
	 
	  var js = JSON.stringify(data);
	  console.log("JS:" + js);

   var xhr = new XMLHttpRequest();
   xhr.open("POST", selectDisapprover_url, true);
   xhr.send(js);
   
   console.log("adding user to disapprover");
   
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      
      var json = JSON.parse(xhr.responseText);
      var isAdded_Status = json["httpCode"];
      if(isAdded_Status == 200){
    	  console.log("team mate added");
    	  console.log("updating display");
    	  processRefreshChoice(xhr.responseText);
      }
      else{
    	  console.log("user already added, attempting to unselect");
    	  // attemps to remove the disapprover from the alt
    	  handleRemoveapprover(js);
    	  
      }
    }
  }
}




//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
function updateAltDisplayRevised(result){
	//ideally, we can just refresh the choice, but that might take a little bit of time
}




function updateAltDisplay(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result); 
  var alt = js["alt"];
  
  var approversList = document.getElementById('approvers');       //just ganna place all my code within this space
  //var disapproverstList = document.getElementById('disapprovers');
  
  /*
  var json = {
		  "alt":{
			"title": "vdjsvdcjvd",
			"approvers" :["iceikking888", "Winnie the Pooo"],
  			"disapprovers" :["candyPop"],
			"feedback" : []
		  },
		  "statusCode" : 200
  };
  */
  
  //var js = JSON.parse(json);
  
  var altTitle = alt["title"];
  var approvers = alt["approvers"];
  var disappovers = alt["disapprovers"];
  var feedback = alt["feedback"];
  
  var output = "";
  output = output +
  "<div id=\"approvers\">" +
  "<h2>" + altTitle + "</h2>" +
  "<h3>Approvers" + approvers.length + "</h3>" +
  "<p>";
  
  for(i = 0; i < approvers.length; i++){
	  if(approvers[i] != null){
		output = output + approvers[i] + "<br>";
		}
  }
  output = output +
  "</p>" +	
  "<h3>Disapprovers" + disappovers.length + "</h3>" + 
  "<p>";
  for(i = 0; i < disappovers.length; i++){
	  if(disappovers[i] != null){
		output = output +disappovers[i] + "<br>";
		}
  }
  output + output +
  "</p>" +
  "</div>";
  

  // Update computation result
  approversList.innerHTML = output;
}
