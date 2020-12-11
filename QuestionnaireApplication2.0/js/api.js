//this is the link from the API gateway after staging
var base_url = "https://q5j6ufetwe.execute-api.us-east-1.amazonaws.com/Beta/";

var create_choice_url = base_url + "/";   // POST
var create_member_url = base_url + "/partipateChoice";   // POST
var choice_list_url = base_url + "/produceReport"; // GET

var selectApprover_url = base_url + "/selectapproval"; //post
var unselectApprover_url = base_url + "/unselectreaction"; // post

var selectDisapprover_url = base_url + "/selectdisapproval"; //post
var unselectDisapprover_url = base_url + "/unselectreaction" ;// post

var updateChoice_url = base_url + "/updatechoice"; //post

var addFeedback_url = base_url + "/createfeedback";  //post

var completed_url = base_url + "/completeChoice" //post

var deleteStale = base_url + "/deleteStale" //post



