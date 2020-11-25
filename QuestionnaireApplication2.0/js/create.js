function processCreateResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  //refreshChoicesList();
}



function handleCreateClick(e) {
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
	  altTitles.push(form.alt1.value)  
  }
  if(form.alt2.value != ""){
	  //return false;
	  altTitles.push(form.alt2.value)  
  }
  if(form.alt3.value != ""){
	  altTitles.push(form.alt3.value)  
  }
  if(form.alt4.value != ""){
	  altTitles.push(form.alt4.value)  
  }
  if(form.alt5.value != ""){
	  altTitles.push(form.alt5.value)  
  }
  
  
  if(form.alt1_d.value != ""){
	  //return false;
	  altTitles.push(form.alt1_d.value)  
  }
  if(form.alt2_d.value != ""){
	  //return false;
	  altTitles.push(form.alt2_d.value)  
  }
  if(form.alt3_d.value != ""){
	  altTitles.push(form.alt3_d.value)  
  }
  if(form.alt4_d.value != ""){
	  altTitles.push(form.alt4_d.value)  
  }
  if(form.alt5_d.value != ""){
	  altTitles.push(form.alt5_d.value)  
  }
  
  
  
  data["alternativeTitles"] = altTitles;
  data["alternatives"] = alts;

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
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("issue with construction")
			  //var js = JSON.parse(xhr.responseText);
			  //var err = js["response"];
			  //alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}