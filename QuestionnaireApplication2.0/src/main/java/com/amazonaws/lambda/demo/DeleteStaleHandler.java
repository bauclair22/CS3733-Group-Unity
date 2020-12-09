package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import database.DAO;
import httpRequestsAndResponses.DeleteStaleRequest;
import httpRequestsAndResponses.DeleteStaleResponse;
import httpRequestsAndResponses.ProduceReportResponse;
import model.ChoiceReport;

public class DeleteStaleHandler implements RequestHandler<DeleteStaleRequest, DeleteStaleResponse> {
	//DeleteStaleResponse
	
	LambdaLogger logger;

	private AmazonS3 s3 = null;

	List<ChoiceReport> deleteStale(float daysOld) throws Exception {

		if (logger != null) {
			logger.log("in deleteStale");
		}
		// Create new ChoiceDAO
		// Check if correct number of alternatives and alternativeTitles

		if (daysOld >= 0) {
			DAO dao = new DAO();
			
			return dao.deleteStaleChoices(daysOld);
		}
		// If parameters incorrect return false
		return null;
	}

	@Override
	public DeleteStaleResponse handleRequest(DeleteStaleRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		DeleteStaleResponse response;
		try {
			List<ChoiceReport> myID = deleteStale(req.getDaysOld());
			logger.log("myID: " + myID);
			if (myID != null) {
				
				response = new DeleteStaleResponse(myID, 200);
			} else {
				response = new DeleteStaleResponse(442,"Unable to delete Stale");
			}
		} catch (Exception e) {
			response = new DeleteStaleResponse(400, "Unable to delete stale: " + "(" + e.getMessage() + ")");
		}

		return response;
	}
}
