package com.amazonaws.lambda.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.UnselectReactionRequest;
import httpRequestsAndResponses.UnselectReactionResponse;

public class UnselectReactionHandler implements RequestHandler<UnselectReactionRequest, UnselectReactionResponse> {
	
	public LambdaLogger logger = null;

    @Override
    public UnselectReactionResponse handleRequest(UnselectReactionRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to unselect reaction");
        
        UnselectReactionResponse response = null;
		logger.log(req.toString());

		DAO dao = new DAO();
		
		//Check if user is in the list of approvers
		ArrayList<String> approvers = new ArrayList<>();
		ArrayList<String> disapprovers = new ArrayList<>();
		try {
			approvers = dao.getLikedBy(req.getAltid());
			disapprovers = dao.getDislikedBy(req.getAltid());
			for(String s : approvers) {
				if(req.getUsername().equals(s)) {
					//Delete user from list
				}
			}
			for(String s : disapprovers) {
				if(req.getUsername().equals(s)) {
					//Delete user from list
				}
			} 
		} catch (Exception e) {
			response = new UnselectReactionResponse(req.getUsername(), 403, "Unable to unselect reaction: " + req.getUsername() + "(" + e.getMessage() + ")");
		}
		
		return response;
    }
}
