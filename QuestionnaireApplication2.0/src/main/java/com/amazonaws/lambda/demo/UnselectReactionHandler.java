package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.SelectApprovalRequest;
import httpRequestsAndResponses.SelectApprovalResponse;
import httpRequestsAndResponses.UnselectReactionRequest;
import httpRequestsAndResponses.UnselectReactionResponse;
import model.TeamMember;

public class UnselectReactionHandler implements RequestHandler<UnselectReactionRequest, UnselectReactionResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public UnselectReactionResponse handleRequest(UnselectReactionRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to approve Alternative");
		
		UnselectReactionResponse response = null;
		logger.log(req.toString());
		
		DAO dao = new DAO();
		
		//In the DAO
		//Check if user has already liked or disliked this alternative
		 try {
			if(dao.addApprover(req.getmemberID() ,req.getAltid())) {
				response = new UnselectReactionResponse(req.getmemberID(), 200);
			}
			
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new UnselectReactionResponse(req.getmemberID(), 403, "Unable to add approval: " + req.getmemberID() + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }
}
