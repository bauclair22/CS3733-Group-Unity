function handleProduceReport(e){
	
	  var xhr = new XMLHttpRequest();
	  xhr.open("GET", choice_list_url, true);
	  xhr.send();

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
	    console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
	    	 if (xhr.status == 200) {
		      console.log ("XHR:" + xhr.responseText);
		      printReport(xhr.responseText);
	    	 } else {
	    		 console.log("issue with construction")
				  //var js = JSON.parse(xhr.responseText);
				  //var err = js["response"];
				  //alert (err);
	    	 }
	    } else {
	      printReport("N/A");
	    }
	  };

}



function printReport(result){
	
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var choiceReport = js["choiceReport"];
	var output = "";
	
	var choiceID;
	var isCompleted;
	var dateCompleted;
	
	
	output = output +
	"<div id=\"choiceReport\"><p>";
	
	for(i = 0; i < choiceReport.length; i++){
		choiceID = choiceReport[i]["choiceID"];
		isCompleted = choiceReport[i]["isCompleted"];
		dateCompleted = choiceReport[i]["dateCompleted"];
		/*
		try{
		dateComplted = choiceReport[i]["dateCompleted"];
		}
		catch(e){
		dateCompleted =  "--";
		}
		*/
		console.log(dateCompleted);
		output = output +
		choiceID + "<br>" +
		isCompleted + "<br>" +
		dateCompleted + "<br><br>";
		
	}
	
	outut = output +
	"</p></div>";
	
	document.getElementById("choiceReport").innerHTML = output;

}