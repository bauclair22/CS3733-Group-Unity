package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.ProduceReportResponse;
import model.Choice;
import model.ChoiceReport;

public class ProduceReportHandler implements RequestHandler<Object, ProduceReportResponse> {
	
	public LambdaLogger logger;
	
	List<ChoiceReport> getChoices() throws Exception {
		logger.log("in getChoices");
		DAO dao = new DAO();
		
		return dao.getAllChoices();
	}

    @Override
    public ProduceReportResponse handleRequest(Object input, Context context) {
    	logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all choices");

		ProduceReportResponse response = null;
		String r ="";
		try {
			List<ChoiceReport> list = getChoices();
			/*
			for(int i=0; i<list.size(); i++) {
				
				r = r.concat(list.get(i).choicereport());
			}
			*/
			response = new ProduceReportResponse(list, 200);
		} catch (Exception e) {
			response = new ProduceReportResponse(403, e.getMessage());
		}
		
		return response;
    }

}
