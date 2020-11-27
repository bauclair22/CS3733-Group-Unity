package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.SelectApprovalRequest;
import httpRequestsAndResponses.SelectApprovalResponse;

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
		 ArrayList<String> approvers = new ArrayList<>();
		 ArrayList<String> disapprovers = new ArrayList<>();
		 try {
			approvers = dao.getLikedBy(req.getAltid());
			disapprovers = dao.getDislikedBy(req.getAltid());
			boolean inList = false;
			boolean inOtherList = false;
			for(String s: approvers) {
				if(s.equals(req.getUsername())) {
					inList = true;
				}
			}
			for(String s: disapprovers) {
				if(s.equals(req.getUsername())) {
					inOtherList = true;
				}
			}
			//If they have not already reacted to this alternative add their approval to the database
			if(!inList && !inOtherList) {
				//Do something here to add approver
			}
			else {
				response = new SelectApprovalResponse(req.getUsername(), 422, "Unable to add approval");
			}
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new SelectApprovalResponse(req.getUsername(), 403, "Unable to add approval: " + req.getUsername() + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }

}
