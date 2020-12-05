
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


/*
function addTeamMemberToAlt(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  

  var approverstList = document.getElementById('approvers');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var constantJson = js.list[i];
    console.log(constantJson);
    
    var cname = constantJson["name"];
    var cval = constantJson["value"];
    var sysvar = constantJson["system"];
    if (sysvar) {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
  }

  // Update computation result
  constList.innerHTML = output;
}
*/