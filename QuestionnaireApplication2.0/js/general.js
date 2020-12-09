

function getAltID(n){
	var altIDString = document.getElementById("altID_meta").content;
	var altID = altIDString.split(",")[n];
	return altID;
}

function setAltID(ids){
	document.getElementById("altID_meta").content; = ids;
}

function getMemberID(){
	return document.getElementById("memberID_meta").context;
}

function setMemberID(id){
	document.getElementById("memberID_meta").context = id;
}

function getChoiceID(){
	return document.getElementById("choiceID_meta").context;
}

function setChoiceID(id){
	document.getElementById("choiceID_meta").context = id;
}


function getFeedbackAltID(){
	return document.getElementById("feedbackAltID_meta").context;
}

function setFeedbackAltID(id){
	document.getElementById("feedbackAltID_meta").context = id;
}

function getFeedbackNum(){
	return document.getElementById("feedbackNum_meta").context;
}

function setFeedbackNum(n){
	document.getElementById("feedbackAltID_meta").context = n;
}

