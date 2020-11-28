package com.amazonaws.lambda.demo;

import java.awt.Choice;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.ProduceReportResponse;

public class ProduceReportHandler implements RequestHandler<Object, ProduceReportResponse> {
	
	public LambdaLogger logger;
	
	List<Choice> getChoices() throws Exception {
		logger.log("in getChoices");
		DAO dao = new DAO();
		
		//Return all choices in the database
		return null;
	}

    @Override
    public ProduceReportResponse handleRequest(Object input, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all choices");

		ProduceReportResponse response;
		try {
			// get all choices
			List<Choice> list = getChoices();
			response = new ProduceReportResponse(list, 200);
		} catch (Exception e) {
			response = new ProduceReportResponse(403, e.getMessage());
		}
		
		return response;
    }

}
