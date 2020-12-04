package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.ProduceReportResponse;
import httpRequestsAndResponses.RefreshChoiceRequest;
import httpRequestsAndResponses.RefreshChoiceResponse;
import model.Choice;

public class RefreshChoiceHandler implements RequestHandler<RefreshChoiceRequest, RefreshChoiceResponse> {

public LambdaLogger logger;

	Choice getChoice(String id) throws Exception {
		logger.log("in getChoice");
		DAO dao = new DAO();
	
		return dao.getChoiceswithID(id);
	}

    @Override
    public RefreshChoiceResponse handleRequest(RefreshChoiceRequest req, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to refresh choice");

		RefreshChoiceResponse response;
		try {
			Choice choice = getChoice(req.getChoiceID());
			response = new RefreshChoiceResponse(choice, 200);
		} catch (Exception e) {
			response = new RefreshChoiceResponse(403, e.getMessage());
		}
		
		return response;
    }

}
