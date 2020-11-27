package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.SelectDisapprovalRequest;
import httpRequestsAndResponses.SelectDisapprovalResponse;

public class SelectDisapprovalHandler implements RequestHandler<SelectDisapprovalRequest, SelectDisapprovalResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public SelectDisapprovalResponse handleRequest(SelectDisapprovalRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to disapprove Alternative");
		
		SelectDisapprovalResponse response = null;
		logger.log(req.toString());
		
		DAO dao = new DAO();
		
		//In the DAO
		//Check if user has already liked or disliked this alternative
		 ArrayList<String> disapprovers = new ArrayList<>();
		 ArrayList<String> approvers = new ArrayList<>();
		 try {
			disapprovers = dao.getDislikedBy(req.getAltid());
			approvers = dao.getLikedBy(req.getAltid());
			boolean inList = false;
			boolean inOtherList = false;
			for(String s: disapprovers) {
				if(s.equals(req.getUsername())) {
					inList = true;
				}
			}
			for(String s: approvers) {
				if(s.equals(req.getUsername())) {
					inOtherList = true;
				}
			}
			//If they have not already reacted to this alternative add their disapproval to the database
			if(!inList && !inOtherList) {
				//Do something here to add disapprover
			}
			else {
				response = new SelectDisapprovalResponse(req.getUsername(), 422, "Unable to add disapproval");
			}
			//Return error message if an exception is caught
		} catch (Exception e) {
			response = new SelectDisapprovalResponse(req.getUsername(), 403, "Unable to add disapproval: " + req.getUsername() + req.getAltid() + "(" + e.getMessage() + ")");
		}
		 return response;
    }

}
