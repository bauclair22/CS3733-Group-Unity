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
	      console.log("issue with produceChoiceReport.js >> handleProduceReport ");
	    }
	  };

}

function clearCompletedChoices(e){
	var days = document.getElementById("daysToDelete").value;
	var data = {};
	data[""] = days;
	var js = JSON.stringify(data);
	
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", clearCompleted_url, true);
	  xhr.send(js);

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
	      console.log("issue with produceChoiceReport.js >> clearCompletedChoices ");
	    }
	  };
}


/**
 * Prints the choices as a table
 * @param result
 * @returns
 */
function printReport(result){
	
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var choiceReport = js["choiceReport"];
	var output = "";
	
	var choiceID;
	var choiceDescription;
	var isCompleted;
	var dateCompleted;
	
	
	output = output +
	"<div id=\"choiceReport\"><table>" + 
	"<tr><th>Choice ID</th><th>Choice Description</th><th>Completed?</th><th>Date Created</th></tr>";
	
	for(i = 0; i < choiceReport.length; i++){
		choiceID = choiceReport[i]["choiceID"];
		choiceDescription = choiceReport[i]["description"];
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
		"<tr>" +
		"<td>" + choiceID + "</td>" +
		"<td>" + choiceDescription + "</td>" +
		"<td>" + isCompleted + "</td>" +
		"<td>" + dateCompleted + "</td>" +
		"</tr>";
		
		//output = output +
		//choiceID + "<br>" +
		//isCompleted + "<br>" +
		//dateCompleted + "<br><br>";
		
	}
	
	outut = output +
	"</table></div>";
	
	document.getElementById("choiceReport").innerHTML = output;

}