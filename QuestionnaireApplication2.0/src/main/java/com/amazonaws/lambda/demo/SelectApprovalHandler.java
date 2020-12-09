package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.SelectApprovalRequest;
import httpRequestsAndResponses.SelectApprovalResponse;
import httpRequestsAndResponses.SelectDisapprovalResponse;
import model.Choice;
import model.TeamMember;

public class SelectApprovalHandler implements RequestHandler<SelectApprovalRequest, SelectApprovalResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public SelectApprovalResponse handleRequest(SelectApprovalRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to approve Alternative");
		
		
		
		SelectApprovalResponse response = null;
		logger.log(req.toString());
		
		DAO dao = new DAO();
		
		//In the DAO
		//Check if user has already liked or disliked this alternative
		 try {
			if(dao.addApprover(req.getmemberID() ,req.getAltid())) {
				Choice myChoice = dao.getChoiceswithID(req.getChoiceID());
				response = new SelectApprovalResponse(myChoice, 200);
			}
			else {
				response = new SelectApprovalResponse(404, "Failed to add approver");
			}
			
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new SelectApprovalResponse(403, "Unable to add approval: " + req.getmemberID() + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }

}
