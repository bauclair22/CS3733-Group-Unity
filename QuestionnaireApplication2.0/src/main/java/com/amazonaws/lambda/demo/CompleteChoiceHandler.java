package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.CompleteChoiceRequest;
import httpRequestsAndResponses.CompleteChoiceResponse;
import httpRequestsAndResponses.SelectApprovalRequest;
import httpRequestsAndResponses.SelectApprovalResponse;
import model.Choice;

public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, CompleteChoiceResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public CompleteChoiceResponse handleRequest(CompleteChoiceRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to complete Choice");
		
		
		
		CompleteChoiceResponse response = null;
		logger.log(req.toString());
		
		DAO dao = new DAO();
		
		//In the DAO
		 try {
			if(dao.completeChoice(req.getChoiceID())) {
				Choice myChoice = dao.getChoiceswithID(req.getChoiceID());
				response = new CompleteChoiceResponse(myChoice, 200);
			}
			else {
				response = new CompleteChoiceResponse(404, "Failed to complete choice");
			}
			
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new CompleteChoiceResponse(403, "Unable to completechoice: " + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }

}
