
/**
 * Loads the create choice form
 * @param e event
 * @returns
 * the creat choice form
 */
function loadCreateChoiceForm(e){
	//if we have time I would like to have the choice only be displayed when in use.
}



/**
 * Respond to server JSON object. And Gives the Choice ID
 *
 */
function processCreateChoiceResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  var js = JSON.parse(result);

  var id = js["response"];
  var status = js["httpCode"];
  
  console.log("status:" + status);
  console.log("id:" + id);
  
  if (status == 200) {
    // Update computation result
	  document.getElementById("choiceID_new").innerHTML = id;
	  document.getElementById("choiceID_meta").content = id;
	  var choiceID_Meta = document.getElementById("choiceID_meta").content;
	  if(choiceID_Meta != "0"){
		  console.log("update to choice meta as been successful: " + choiceID_Meta )
	  }
  } else {
    var msg = js["error"];
    document.getElementById("choiceID_new").innerHTML = "Sorry, there seems to be an issue with your submission. Try Again.";
  }
  
}

/**
 * After the user has filled out the form, the data is sent back to get a response back
 * input: 	"description"
 * 			"numMembers"
 * output:	"response"  -> choice id
 * 			"httpCode"
 * 			"error messages"
 * 
 */
function handleCreateClick(e) {
	console.log("Starting to porcess Choice");
	document.getElementById("choiceID_new").innerHTML = "Processing our choice..please wait for a moment";
	
  var form = document.createChoiceForm;
 
  var data = {};
  
  //convert everything that was in the hmtl form to the lamda
  
  //data["title"] = form.titleInput.value;
  data["description"] = form.dInput.value;
  data["numMembers"] = form.maxUsers.value;
  
  var altTitles = [];
  var alts = [];
  
  
  if(form.alt1.value != ""){
	  //return false;
	  altTitles.push(form.alt1.value);  
  }
  if(form.alt2.value != ""){
	  //return false;
	  altTitles.push(form.alt2.value); 
  }
  if(form.alt3.value != ""){
	  altTitles.push(form.alt3.value); 
  }
  if(form.alt4.value != ""){
	  altTitles.push(form.alt4.value);
  }
  if(form.alt5.value != ""){
	  altTitles.push(form.alt5.value);
  }
  
  
  data["alternativeTitles"] = altTitles;

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", create_choice_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateChoiceResponse(xhr.responseText);
    	 } else {
    		 console.log("issue with construction")
			  //var js = JSON.parse(xhr.responseText);
			  //var err = js["response"];
			  //alert (err);
    	 }
    } else {
      processCreateChoiceResponse("N/A");
    }
  };
}