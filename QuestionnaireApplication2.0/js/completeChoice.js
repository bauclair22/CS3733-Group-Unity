function handleCompleteChoice(e, altNum){
	var data = {};
	
	data["altid"] = getAltID(altNum);
	data["choiceID"] = getChoiceID();
	
	var js = JSON.stringify(data);
	
	   var xhr = new XMLHttpRequest();
	   xhr.open("POST", completed_url , true);  //makes sure to call the lamda function
	   xhr.send(js);
	   
	   console.log("Complete Choice");

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	      console.log ("XHR:" + xhr.responseText);
	      processRefreshChoice(xhr.responseText, altNum);
	    } else {
	    	processRefreshChoice("N/A");
	    }
	  };
}


