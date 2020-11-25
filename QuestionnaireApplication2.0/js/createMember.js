function processCreateResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  //refreshChoicesList();
}

function handleCreateMemberClick(e) {
  var form = document.SignInForm;
 
  var data = {};
  
  //convert everything that was in the hmtl form to the lamda
  
  //data["title"] = form.titleInput.value;
  data["username"] = form.username.value;
  data["password"] = form.password.value;
  data["id"] = form.choiceId.value;
 

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  
  xhr.open("POST", create_member_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("issue with adding member - login -")
			  //var js = JSON.parse(xhr.responseText);
			  //var err = js["response"];
			  //alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}