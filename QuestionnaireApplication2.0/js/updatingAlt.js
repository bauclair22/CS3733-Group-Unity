
function handleUpdatingAltClick(e) {

   var xhr = new XMLHttpRequest();
   xhr.open("POST", selectApprover_url, true);
   xhr.send();
   
   console.log("sent request to add user to appoval");
   
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      
      var js = JSON.parse(xhr.responseText);
      var isAdded_Status = js["statusCode"];
      if(isAdded_Status == 200){
    	  console.log("team mate added");
      }
      else{
    	  
    	  console.log("attempting to remove user from Alt");
    	  xhr = new XMLHttpRequest();
    	   xhr.open("POST", unselectApprover_url, true);
    	   xhr.send();
    	   
    	   js = JSON.parse(xhr.responseText);
    	   var isRemoved_Status = js["statusCode"];
    	   if(isRemoved_Status == 200){
    		   console.log("team mate removed"); 		   
    	   }
    	   else{
    		   console.log("unable to process request")
    	   }
      }
    }
}



function addTeamMemberToAlt(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var approversList = document.getElementById('approvers');       //just ganna place all my code within this space
  //var disapproverstList = document.getElementById('disapprovers');
  
  var altTitle = js["title"];
  var approvers = js["approvers"];
  var disappovers = js["disapprovers"];
  var feedback = js["feedback"];
  
  var output = "";
  output = output +
  "<div id=\"approvers\">" +
  "<h2>" + altTitle + "</h2>" +
  "<label>Approvers</label>" +
  "<p>";
  
  for(i = 0; i < approvers.length; i++){
	  if(approvers[i] != null){
		output = output + approvers[i] + "<br>";
		}
  }
  output = output +
  "</p>" +	
  "<label>Disapprovers (Not Installed) </label>" +
  "</div>";
  

  // Update computation result
  approversList.innerHTML = output;
}
