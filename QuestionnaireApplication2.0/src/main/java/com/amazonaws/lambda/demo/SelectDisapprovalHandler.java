package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.SelectApprovalRequest;
import httpRequestsAndResponses.SelectApprovalResponse;
import httpRequestsAndResponses.SelectDisapprovalRequest;
import httpRequestsAndResponses.SelectDisapprovalResponse;
import model.TeamMember;

public class SelectDisapprovalHandler implements RequestHandler<SelectDisapprovalRequest, SelectDisapprovalResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public SelectDisapprovalResponse handleRequest(SelectDisapprovalRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to approve Alternative");
		
		SelectDisapprovalResponse response = null;
		logger.log(req.toString());
		
		DAO dao = new DAO();
		
		//In the DAO
		//Check if user has already liked or disliked this alternative
		 try {
			if(dao.addDisapprover(req.getmemberID() ,req.getAltid())) {
				response = new SelectDisapprovalResponse(req.getmemberID(), 200);
			}
			
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new SelectDisapprovalResponse(req.getmemberID(), 403, "Unable to add disapproval: " + req.getmemberID() + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }

}
