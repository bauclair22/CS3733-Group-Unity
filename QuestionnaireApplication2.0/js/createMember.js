function processCreateMemberResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
	var formMessage = document.getElementById("SignInMessage");
	console.log("result:" + result);

	var json = JSON.parse(result);
	var status = json["httpCode"];
	//var isCompleted = json["choice"]["isCompleted"];
	//422 -> the choice is completed
	//200 -> okay
	
	if(status == 200){
		//if(isCompleted){
		//	formMessage.innerHTML = "The choice is already Complete";
		//}
		//else{
			openNewChoice(result);
		//}
	}
	else{
		var error = json["response"];
		formMessage.innerHTML = error;
	}

}

function handleCreateMemberClick(e) {
  var form = document.SignInForm;
  var formMessage = document.getElementById("SignInMessage");
  var data = {};
  
  formMessage.innerHTML = "Wait as we process your information";
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
    	
    	var json = JSON.parse(xhr.responseText);
    	var status = json["httpCode"];
    	var isCompleted = json["choice"]["isCompleted"];
    	
    	 if (xhr.status == 200) {
    		 console.log ("XHR:" + xhr.responseText);
    		 if(isCompleted){
    			 formMessage.innerHTML = "The choice is already Complete";
    		 }
    		 else{
    			  formMessage.innerHTML = "Welcome " + form.username.value;
    		 processCreateMemberResponse(xhr.responseText);
    		 }
    	 } else {
    		 console.log("issue with adding member - login -")
    		 formMessage.innerHTML = "There seems to be an issue with your sign in";
			  //var js = JSON.parse(xhr.responseText);
			  //var err = js["response"];
			  //alert (err);
    	 }
    } else {
      processCreateMemberResponse("N/A");
    }
  };
}